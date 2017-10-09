/*
 * Copyright (C) 2017 Jacksgong(jacksgong.com)
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

package cn.dreamtobe.okdownload.task;

import android.net.Uri;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import cn.dreamtobe.okdownload.DownloadTask;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DownloadTaskTest {

    @Test
    public void addHeader() throws Exception {
        final String mockUrl = "mock url";
        final Uri fileUri = mock(Uri.class);
        DownloadTask task = new DownloadTask(mockUrl, fileUri);


        final String mockKey1 = "mock key1";
        final String mockKey2 = "mock key2";
        final String mockValue1 = "mock value1";
        final String mockValue2 = "mock value2";

        task.addHeader(mockKey1, mockValue1);
        task.addHeader(mockKey1, mockValue2);

        task.addHeader(mockKey2, mockValue2);

        final Map<String, List<String>> headerMap = task.getHeaderMapFields();
        assertThat(headerMap).isNotNull();

        assertThat(headerMap).containsKey(mockKey1).containsKey(mockKey2);

        final List<String> key1Values = headerMap.get(mockKey1);
        assertThat(key1Values).containsOnly(mockValue1, mockValue2);

        final List<String> key2Values = headerMap.get(mockKey2);
        assertThat(key2Values).containsOnly(mockValue2);
    }

}