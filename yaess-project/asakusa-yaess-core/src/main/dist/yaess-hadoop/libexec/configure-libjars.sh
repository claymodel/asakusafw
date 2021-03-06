#
# Copyright 2011-2013 Asakusa Framework Team.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

if [ -d "$ASAKUSA_HOME/core/lib" ]
then
    for f in $(ls "$ASAKUSA_HOME/core/lib")
    do
        if [ "$_YS_LIBJARS" = "" ]
        then
            _YS_LIBJARS="$ASAKUSA_HOME/core/lib/$f"
        else
            _YS_LIBJARS="$_YS_LIBJARS,$ASAKUSA_HOME/core/lib/$f"
        fi
    done
fi

if [ "$_OPT_BATCH_ID" != "" -a -d "$ASAKUSA_HOME/batchapps/$_OPT_BATCH_ID/usr/lib" ]
then
    _OPT_LIBRARIES_PATH="$ASAKUSA_HOME/batchapps/$_OPT_BATCH_ID/usr/lib"
    for f in $(ls "$_OPT_LIBRARIES_PATH")
    do
        if [ -f "$_OPT_LIBRARIES_PATH/$f" ]
        then
            if [ "$_YS_LIBJARS" = "" ]
            then
                _YS_LIBJARS="$_OPT_LIBRARIES_PATH/$f"
            else
                _YS_LIBJARS="$_YS_LIBJARS,$_OPT_LIBRARIES_PATH/$f"
            fi
        fi
    done
fi

if [ -d "$ASAKUSA_HOME/ext/lib" ]
then
    for f in $(ls "$ASAKUSA_HOME/ext/lib")
    do
        if [ "$_YS_LIBJARS" = "" ]
        then
            _YS_LIBJARS="$ASAKUSA_HOME/ext/lib/$f"
        else
            _YS_LIBJARS="$_YS_LIBJARS,$ASAKUSA_HOME/ext/lib/$f"
        fi
    done
fi
