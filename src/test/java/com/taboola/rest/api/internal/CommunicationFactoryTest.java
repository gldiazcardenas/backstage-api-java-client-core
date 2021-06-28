package com.taboola.rest.api.internal;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.taboola.rest.api.exceptions.RestAPIException;
import com.taboola.rest.api.exceptions.factories.DefaultExceptionFactory;
import com.taboola.rest.api.internal.config.CommunicationConfig;
import com.taboola.rest.api.internal.config.SerializationConfig;
import com.taboola.rest.api.internal.config.UserAgentHeader;

/**
 * Created by vladi
 * Date: 6/27/2021
 * Time: 12:00 PM
 * By Taboola
 */
public class CommunicationFactoryTest {

    private CommunicationFactory testInstance;

    public interface TestEndpoint {
            @GET("/users/current/allowed-accounts")
            @Headers("Content-Type: application/json")
            Object getTestObject(@Header("Authorization") String accessToken) throws RestAPIException;
    }

    @Before
    public void beforeTest() {
        CommunicationConfig communicationConfig = new CommunicationConfig("http://localhost",
                1L, 1L, 1L, 1, 60L,
                Collections.singleton(new UserAgentHeader("Dummy-Agent")),true, new DefaultExceptionFactory());
        SerializationConfig serializationConfig = new SerializationConfig();
        testInstance = new CommunicationFactory(communicationConfig, serializationConfig);
    }

    @Test
    public void testHappyFlowServices() {
        Assert.assertNotNull("Missing service instance", testInstance.createRetrofitEndpoint(TestEndpoint.class));
    }
}