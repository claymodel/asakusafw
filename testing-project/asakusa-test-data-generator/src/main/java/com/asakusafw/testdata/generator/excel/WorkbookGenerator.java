/**
 * Copyright 2011-2013 Asakusa Framework Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.asakusafw.testdata.generator.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asakusafw.dmdl.semantics.ModelDeclaration;
import com.asakusafw.testdata.generator.TemplateGenerator;

/**
 * Generates Excel workbooks for testing each data model.
 * @since 0.2.0
 * @version 0.5.0
 */
public class WorkbookGenerator implements TemplateGenerator {

    static final Logger LOG = LoggerFactory.getLogger(WorkbookGenerator.class);

    private final File output;

    private final WorkbookFormat format;

    /**
     * Creates a new instance.
     * @param output output directory
     * @param format workbook format to be generated
     * @throws IllegalArgumentException if some parameters were {@code null}
     */
    public WorkbookGenerator(File output, WorkbookFormat format) {
        if (output == null) {
            throw new IllegalArgumentException("output must not be null"); //$NON-NLS-1$
        }
        if (format == null) {
            throw new IllegalArgumentException("format must not be null"); //$NON-NLS-1$
        }
        this.output = output;
        this.format = format;
    }

    /**
     * Generates a workbook for the specified model.
     * @param model the target model
     * @throws IOException if failed to generate a workbook
     * @throws IllegalArgumentException if some parameters were {@code null}
     */
    @Override
    public void generate(ModelDeclaration model) throws IOException {
        if (model == null) {
            throw new IllegalArgumentException("model must not be null"); //$NON-NLS-1$
        }
        if (output.isDirectory() == false && output.mkdirs() == false) {
            throw new IOException(MessageFormat.format(
                    "出力先のディレクトリを生成できませんでした: {0}",
                    output));
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        SheetBuilder builder = new SheetBuilder(workbook, model);
        for (SheetFormat sheet : format.getSheets()) {
            switch (sheet.getKind()) {
            case DATA:
                LOG.debug("Building data sheet: {}.{}", model.getName(), sheet.getName());
                builder.addData(sheet.getName());
                break;
            case RULE:
                LOG.debug("Building rule sheet: {}.{}", model.getName(), sheet.getName());
                builder.addRule(sheet.getName());
                break;
            default:
                throw new AssertionError(MessageFormat.format(
                        "Unknown sheet format: {0}",
                        sheet));
            }
        }

        File file = new File(output, format.getFileName(model));
        LOG.debug("Emitting workbook: {}", file);

        OutputStream out = new FileOutputStream(file);
        try {
            workbook.write(out);
        } finally {
            out.close();
        }
        LOG.info(MessageFormat.format(
                "Excelワークブックを生成しました: {0}",
                file.getAbsolutePath()));
    }

    @Override
    public String getTitle() {
        return MessageFormat.format(
                "Excelワークブックの生成 ({0})",
                format);
    }
}
