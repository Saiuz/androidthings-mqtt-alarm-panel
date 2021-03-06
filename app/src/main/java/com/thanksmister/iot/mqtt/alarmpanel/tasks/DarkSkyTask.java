/*
 * <!--
 *   ~ Copyright (c) 2017. ThanksMister LLC
 *   ~
 *   ~ Licensed under the Apache License, Version 2.0 (the "License");
 *   ~ you may not use this file except in compliance with the License. 
 *   ~ You may obtain a copy of the License at
 *   ~
 *   ~ http://www.apache.org/licenses/LICENSE-2.0
 *   ~
 *   ~ Unless required by applicable law or agreed to in writing, software distributed 
 *   ~ under the License is distributed on an "AS IS" BASIS, 
 *   ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *   ~ See the License for the specific language governing permissions and 
 *   ~ limitations under the License.
 *   -->
 */

package com.thanksmister.iot.mqtt.alarmpanel.tasks;

import com.thanksmister.iot.mqtt.alarmpanel.network.fetchers.DarkSkyFetcher;
import com.thanksmister.iot.mqtt.alarmpanel.network.model.DarkSkyResponse;

import retrofit2.Call;
import retrofit2.Response;

public class DarkSkyTask extends NetworkTask<String, Void, Response<DarkSkyResponse>> {
    
    private DarkSkyFetcher fetcher;

    public DarkSkyTask(DarkSkyFetcher fetcher) {
        this.fetcher = fetcher;
    }

    protected Response<DarkSkyResponse> doNetworkAction(String... params) throws Exception {
        if (params.length != 4) {
            throw new Exception("Wrong number of params, expected 4, received " + params.length);
        }
        
        String apiKey = params[0];
        String units = params[1];
        String lat = params[2];
        String lon = params[3];

        Call<DarkSkyResponse> call = fetcher.getExtendedFeedData(apiKey, units, lat, lon);
        return call.execute();
    }
}