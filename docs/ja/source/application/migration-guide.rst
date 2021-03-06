==============================
開発環境マイグレーションガイド
==============================
この文書では、Asakusa Framework のバージョンアップに伴う、開発環境のマイグレーション手順について解説します。

各バージョン共通のマイグレーション手順
======================================
開発環境のAsakusa Frameworkをマイグレーションするための、各バージョン共通のマイグレーション手順を示します。

開発環境をマイグレーションする際には、ここで説明しているマイグレーション手順が基本となりますが、
これに加えて後述する `バージョン固有のマイグレーション手順`_ が必要となる場合があります。

マイグレーションを行う際には、必ず `バージョン固有のマイグレーション手順`_ も確認してください。

pom.xmlのバージョンを更新
-------------------------
pom.xmlの10行目にある ``<asakusafw.version>`` の値を更新したいバージョンに書き換えます。

..  code-block:: sh

    <asakusafw.version>0.5.1</asakusafw.version>

アーキタイプごとの利用可能なバージョンはアーキタイプカタログに公開しています。詳しくは :doc:`../application/maven-archetype` の :ref:`archetype-catalog` を参照してください。

Asakusa Frameworkの再インストール
---------------------------------
アップデートするバージョンのAsakusa Frameworkを開発環境に再インストールします。

Asakusa Frameworkを再インストールするには、Framework Organizer [#]_ に対してMavenの以下のコマンドを実行して生成します。

..  code-block:: sh
    
    mvn package antrun:run

..  [#] 詳しくは、 :doc:`../administration/framework-organizer` を参照してください。

アプリケーションのバッチコンパイル
----------------------------------
指定したバージョンのAsakusa Frameworkでバッチアプリケーションをバッチコンパイルします。

..  code-block:: sh

    mvn clean package

..  attention::
    基本的にAsakusa Frameworkのマイグレーションを行う場合、合わせてバッチアプリケーションの再コンパイルが必要です。運用環境のAsakusa Frameworkをマイグレーションする場合は、これに合わせて該当バージョンでバッチコンパイルを行ったアプリケーションアーカイブを再デプロイしてください。


Eclipse定義ファイルの更新
-------------------------
Eclipseを使って開発している場合は、Eclipse用定義ファイルを更新します。

..  code-block:: sh

    mvn eclipse:eclipse


バージョン固有のマイグレーション手順
====================================
開発環境のAsakusa Frameworkをマイグレーションするための、バージョン固有のマイグレーション手順を示します。

なお、複数バージョンをまたいだマイグレーションを行う場合 (例えば バージョン 0.2.3 から 0.2.6 へバージョンアップを行う場合)、中間のバージョンの手順 (ここの例では 0.2.4, および 0.2.5) も確認し、パッチ適用手順などが提供されていた場合は、必ずそのパッチを順次適用するようにしてください。

0.5.1 へのマイグレーション
--------------------------

pom.xmlの変更
~~~~~~~~~~~~~
バージョン0.5.1ではアプリケーションプロジェクトに含まれるpom.xmlに変更が行われたため、以下のパッチファイルを適用してpom.xmlを0.5.1向けに変更してください。

* アーキタイプ: ``asakusa-archetype-thundergate``  (ThunderGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-thundergate-051pom.patch <migration/asakusa-archetype-thundergate-051pom.patch>`
* アーキタイプ: ``asakusa-archetype-windgate``  (WindGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-windgate-051pom.patch <migration/asakusa-archetype-windgate-051pom.patch>`
* アーキタイプ: ``asakusa-archetype-directio``  (Direct I/Oを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-directio-051pom.patch <migration/asakusa-archetype-directio-051pom.patch>`

上記のパッチをpatchコマンドなどを使用して適用します。以下パッチファイルを ``/tmp`` に配置した場合の適用例です。

..  code-block:: sh

    cd app-project
    patch < /tmp/asakusa-archetype-windgate-051pom.patch

pom.xmlを手動で変更している場合、パッチファイルがそのまま適用出来ないかもしれません。その場合、パッチファイルの内容を確認して手動で変更を取り込むか、バージョン0.5.1のアーキタイプからプロジェクトを生成し、その中に含まれるpom.xmlに対してアプリケーション側で変更した内容を反映させたものを使用してください。

0.5.0 へのマイグレーション
--------------------------

pom.xmlの変更
~~~~~~~~~~~~~
バージョン0.5.0ではアプリケーションプロジェクトに含まれるpom.xmlに変更が行われたため、以下のパッチファイルを適用してpom.xmlを0.5.0向けに変更してください。

* アーキタイプ: ``asakusa-archetype-thundergate``  (ThunderGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-thundergate-050pom.patch <migration/asakusa-archetype-thundergate-050pom.patch>`
* アーキタイプ: ``asakusa-archetype-windgate``  (WindGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-windgate-050pom.patch <migration/asakusa-archetype-windgate-050pom.patch>`
* アーキタイプ: ``asakusa-archetype-directio``  (Direct I/Oを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-directio-050pom.patch <migration/asakusa-archetype-directio-050pom.patch>`

上記のパッチをpatchコマンドなどを使用して適用します。以下パッチファイルを ``/tmp`` に配置した場合の適用例です。

..  code-block:: sh

    cd app-project
    patch < /tmp/asakusa-archetype-windgate-050pom.patch

pom.xmlを手動で変更している場合、パッチファイルがそのまま適用出来ないかもしれません。その場合、パッチファイルの内容を確認して手動で変更を取り込むか、バージョン0.5.0のアーキタイプからプロジェクトを生成し、その中に含まれるpom.xmlに対してアプリケーション側で変更した内容を反映させたものを使用してください。

``hadoop.version`` の変更
~~~~~~~~~~~~~~~~~~~~~~~~~
バージョン0.5.0では Apache Hadoop 1.1.2 がデフォルトで依存するHadoopのライブラリバージョンとなりました。

0.4.0 へのマイグレーション
--------------------------

pom.xmlの変更
~~~~~~~~~~~~~
バージョン0.4.0ではアプリケーションプロジェクトに含まれるpom.xmlに変更が行われたため、以下のパッチファイルを適用してpom.xmlを0.4.0向けに変更してください。

* アーキタイプ: ``asakusa-archetype-thundergate``  (ThunderGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-thundergate-040pom.patch <migration/asakusa-archetype-thundergate-040pom.patch>`
* アーキタイプ: ``asakusa-archetype-windgate``  (WindGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-windgate-040pom.patch <migration/asakusa-archetype-windgate-040pom.patch>`
* アーキタイプ: ``asakusa-archetype-directio``  (Direct I/Oを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-directio-040pom.patch <migration/asakusa-archetype-directio-040pom.patch>`

上記のパッチをpatchコマンドなどを使用して適用します。以下パッチファイルを ``/tmp`` に配置した場合の適用例です。

..  code-block:: sh

    cd app-project
    patch < /tmp/asakusa-archetype-windgate-040pom.patch

pom.xmlを手動で変更している場合、パッチファイルがそのまま適用出来ないかもしれません。その場合、パッチファイルの内容を確認して手動で変更を取り込むか、バージョン0.4.0のアーキタイプからプロジェクトを生成し、その中に含まれるpom.xmlに対してアプリケーション側で変更した内容を反映させたものを使用してください。

不要ファイルの削除
~~~~~~~~~~~~~~~~~~
アプリケーションプロジェクトに含まれる ``src/main/assembly`` ディレクトリとその配下に含まれるファイルは不要になりました。このディレクトリは削除することができます。

``hadoop.version`` の変更
~~~~~~~~~~~~~~~~~~~~~~~~~
バージョン0.4.0ではCDH3 Update 5をデフォルトの依存バージョンとしており、動作検証もこのバージョンで実施しているため、アプリケーションプロジェクトの依存バージョンもこれに合わせることを推奨します。

上記のpom.xmlのパッチを適用すると依存するCDH3のバージョン定義がCDH3 Update 5に変更されるので、開発環境にインストールしたHadoopもCDH3 Update 5にアップデートすることを推奨します。


0.2.6 へのマイグレーション
--------------------------

アセンブリディスクリプタの変更
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
バージョン0.2.6ではアプリケーションプロジェクトに含まれるアセンブリディスクリプタ (プロジェクトの ``src/main/assembly`` 配下のファイル) が変更になったため、これらのファイルをバージョン0.2.6が提供するファイルに変更してください。変更手順は以下の通りです。

1. バージョン0.2.6のアーキタイプからダミーのプロジェクトを任意のディレクトリに作成する。
2. 作成したプロジェクトの ``src/main/assembly`` に含まれるすべてのファイルを既存のアプリケーションプロジェクトの `src/main/assembly` 配下にコピーする。
3. 1で作成したダミーのプロジェクトを削除する。

pom.xmlの変更
~~~~~~~~~~~~~
バージョン0.2.6ではアプリケーションプロジェクトに含まれるpom.xmlに変更が行われたため、以下のパッチファイルを適用してpom.xmlを0.2.6向けに変更してください。

* アーキタイプ: ``asakusa-archetype-thundergate``  (ThunderGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-thundergate-026pom.patch <migration/asakusa-archetype-thundergate-026pom.patch>`
* アーキタイプ: ``asakusa-archetype-windgate``  (WindGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-windgate-026pom.patch <migration/asakusa-archetype-windgate-026pom.patch>`
* アーキタイプ: ``asakusa-archetype-directio``  (Direct I/Oを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-directio-026pom.patch <migration/asakusa-archetype-directio-026pom.patch>`

上記のパッチをpatchコマンドなどを使用して適用します。以下パッチファイルを ``/tmp`` に配置した場合の適用例です。

..  code-block:: sh

    cd app-project
    patch < /tmp/asakusa-archetype-windgate-026pom.patch

pom.xmlを手動で変更している場合、パッチファイルがそのまま適用出来ないかもしれません。その場合、パッチファイルの内容を確認して手動で変更を取り込むか、バージョン0.2.6のアーキタイプからプロジェクトを生成し、その中に含まれるpom.xmlに対してアプリケーション側で変更した内容を反映させたものを使用してください。

CDHバージョンの変更
~~~~~~~~~~~~~~~~~~~
バージョン0.2.6ではCDH3 Update 4をデフォルトの依存バージョンとしており、動作検証もこのバージョンで実施しているため、アプリケーションプロジェクトの依存バージョンもこれに合わせることを推奨します。

上記のpom.xmlのパッチを適用すると依存するCDH3のバージョン定義がCDH3 Update 4に変更されるので、開発環境にインストールしたHadoopもCDH3 Update 4にアップデートすることを推奨します。

0.2.5 へのマイグレーション
--------------------------

アセンブリディスクリプタの変更
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
バージョン0.2.5ではアプリケーションプロジェクトに含まれるアセンブリディスクリプタ (プロジェクトの ``src/main/assembly`` 配下のファイル) が追加/変更になったため、これらのファイルをバージョン0.2.5が提供するファイルに変更してください。変更手順は以下の通りです。

1. バージョン0.2.5のアーキタイプからダミーのプロジェクトを任意のディレクトリに作成する。
2. 作成したプロジェクトの ``src/main/assembly`` に含まれるすべてのファイルを既存のアプリケーションプロジェクトの `src/main/assembly` 配下にコピーする。
3. 1で作成したダミーのプロジェクトを削除する。

pom.xmlの変更
~~~~~~~~~~~~~
バージョン0.2.5ではアプリケーションプロジェクトに含まれるpom.xmlに変更が行われたため、以下のパッチファイルを適用してpom.xmlを0.2.5向けに変更してください。

* アーキタイプ: ``asakusa-archetype-thundergate``  (ThunderGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-thundergate-025pom.patch <migration/asakusa-archetype-thundergate-025pom.patch>`
* アーキタイプ: ``asakusa-archetype-windgate``  (WindGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-windgate-025pom.patch <migration/asakusa-archetype-windgate-025pom.patch>`

上記のパッチをpatchコマンドなどを使用して適用します。以下パッチファイルを ``/tmp`` に配置した場合の適用例です。

..  code-block:: sh

    cd app-project
    patch < /tmp/asakusa-archetype-windgate-025pom.patch

pom.xmlを手動で変更している場合、パッチファイルがそのまま適用出来ないかもしれません。その場合、パッチファイルの内容を確認して手動で変更を取り込むか、バージョン0.2.5のアーキタイプからプロジェクトを生成し、その中に含まれるpom.xmlに対してアプリケーション側で変更した内容を反映させたものを使用してください。

0.2.4 へのマイグレーション
--------------------------

アセンブリディスクリプタの変更
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
バージョン0.2.4ではアプリケーションプロジェクトに含まれるアセンブリディスクリプタ (プロジェクトの ``src/main/assembly`` 配下のファイル) が追加/変更になったため、これらのファイルをバージョン0.2.4が提供するファイルに変更してください。変更手順は以下の通りです。

1. バージョン0.2.4のアーキタイプからダミーのプロジェクトを任意のディレクトリに作成する。
2. 作成したプロジェクトの ``src/main/assembly`` に含まれるすべてのファイルを既存のアプリケーションプロジェクトの `src/main/assembly` 配下にコピーする。
3. 1で作成したダミーのプロジェクトを削除する。

pom.xmlの変更
~~~~~~~~~~~~~
バージョン0.2.4ではアプリケーションプロジェクトに含まれるpom.xmlに変更が行われたため、以下のパッチファイルを適用してpom.xmlを0.2.4向けに変更してください。

* アーキタイプ: ``asakusa-archetype-batchapp``  (ThunderGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-batchapp-024pom.patch <migration/asakusa-archetype-batchapp-024pom.patch>`
* アーキタイプ: ``asakusa-archetype-windgate``  (WindGateを使用したアプリケーション向け) 用パッチファイル
   * :download:`asakusa-archetype-windgate-024pom.patch <migration/asakusa-archetype-windgate-024pom.patch>`

上記のパッチをpatchコマンドなどを使用して適用します。以下パッチファイルを ``/tmp`` に配置した場合の適用例です。

..  code-block:: sh

    cd app-project
    patch < /tmp/asakusa-archetype-windgate-024pom.patch

pom.xmlを手動で変更している場合、パッチファイルがそのまま適用出来ないかもしれません。その場合、パッチファイルの内容を確認して手動で変更を取り込むか、バージョン0.2.4のアーキタイプからプロジェクトを生成し、その中に含まれるpom.xmlに対してアプリケーション側で変更した内容を反映させたものを使用してください。

WindGateの仕様変更
~~~~~~~~~~~~~~~~~~
WindGateは本バージョンからCSV連携モジュールが追加となり、またWindGateのデフォルトコンフィグレーションはDBMS連携用の設定からCSV連携用の設定に変更されました。また、プロファイル定義ファイルに設定可能ないくつかの項目が追加されました。そのほか、WindGate用のアーキタイプから生成されるサンプルプログラムは、CSV連携用のアプリケーションに変更されています。

過去バージョンで作成したDBMS連携向けアプリケーションはそのまま動作しますが、バージョン0.2.4で追加された機能を使用する場合は、 WindGateのドキュメント :doc:`../windgate/user-guide` を参照して下さい。

0.2.3 へのマイグレーション
--------------------------
バージョン0.2.3ではThunderGateのキャッシュ機能、及びYAESSが追加されたため、必要に応じて DSLの仕様変更、及び開発環境の構成変更に対応する必要があります。

ジョブフローDSLの仕様変更
~~~~~~~~~~~~~~~~~~~~~~~~~
*(ThunderGate用アーキタイプ asakusa-archetype-batchapp から生成したアプリケーションプロジェクトについては、以下の変更を行なってください。)*

ジョブフローDSLのThunderGate用インポータ記述用親クラス (DbImporterDescription [#]_ ) において、キャッシュ有効/無効を指定するメソッド isCacheEnabled() がデフォルト実装され、戻り値 ``false`` を返すようになりました。

また、 ThunderGate用アーキタイプ ``asakusa-archetype-batchapp`` から生成されるサンプルアプリケーションのインポータ記述用親クラス (DefaultDbImporterDescription) のisCacheEnabled() メソッドが削除されました。

これらの変更の目的は、バージョン0.2.3で追加されたThunderGateキャッシュ機能について、デフォルトではキャッシュOFF（過去バージョンと同じ動作）とするためですが、バージョン0.2.2までの DefaultDbImporterDescription をそのまま実装しているアプリケーションについては、isCacheEnabled() が ``true`` を返すよう実装されているため意図せずキャッシュがONに設定される可能性があるため、アプリケーションの実装を確認の上、必要であれば ソースを修正してください。

..  [#] com.asakusafw.vocabulary.bulkloader.DbImporterDescription

build.propertiesの項目追加/変更
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*(ThunderGate用アーキタイプ asakusa-archetype-batchapp から生成したアプリケーションプロジェクトについては、以下の変更を行なってください。)*

* ThunderGateキャッシュ機能用のプロパティ追加
   * asakusa.modelgen.sid.column
   * asakusa.modelgen.timestamp.column
   * asakusa.modelgen.delete.column
   * asakusa.modelgen.delete.value
* asakusa.modelgen.excludes のデフォルトが変更
   * ThunderGateが使用するテーブルについてはデフォルトでモデル生成対象から除外されるようになったため、このプロパティで除外指定を行う必要がなくなりました。

バージョン0.2.3の変更箇所を以下に示します。以下の定義をアプリケーションプロジェクトの build.properties に追加した上で、必要に応じてアプリケーション毎に適切な値に変更して下さい。

..  code-block:: properties

    # A regular expression string which excludes model name with model generation.
    asakusa.modelgen.excludes=.*_RL
    # The system ID column name (optional).
    asakusa.modelgen.sid.column=SID
    # The last modified timestamp column name (optional).
    asakusa.modelgen.timestamp.column=UPDT_DATETIME
    # The logical delete flag column name (optional).
    asakusa.modelgen.delete.column=DELETE_FLAG
    # Logical delete flag value (optional).
    asakusa.modelgen.delete.value="1"

ビルドスクリプトの更新
~~~~~~~~~~~~~~~~~~~~~~
*(この変更はすべてのアプリケーションプロジェクトに対して実施してください)*

アプリケーションプロジェクトの以下のファイルを、バージョン0.2.3のアーキタイプ(asakusa-archetype-batchapp もしくは asakusa-archetype-windgate)から生成したプロジェクトに含まれるファイルで上書き更新してください。

* src/main/assembly/asakusa-install-dev.xml
* src/main/scripts/asakusa-build.xml

YAESS用依存定義の追加
~~~~~~~~~~~~~~~~~~~~~
*(この変更はYAESSを使用する場合に実施して下さい)*

YAESSを使用する場合、アプリケーションプロジェクトのpom.xmlについて、以下のdependencyを追加してください。

..  code-block:: xml

        <dependency>
            <groupId>com.asakusafw</groupId>
            <artifactId>asakusa-yaess-plugin</artifactId> <version>${asakusafw.version}</version>
        </dependency>

CDHバージョンの変更
~~~~~~~~~~~~~~~~~~~
バージョン0.2.3ではCDH3 Update2をデフォルトの依存バージョンとしており、動作検証もこのバージョンで実施しているため、アプリケーションプロジェクトの依存バージョンもこれに合わせることを推奨します。

アプリケーションプロジェクトのpom.xmlについて、以下の変更を行ってください。

..  code-block:: xml

    <cloudera.cdh.version>0.20.2-cdh3u2</cloudera.cdh.version>

