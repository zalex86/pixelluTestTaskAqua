package com.pixellu.helpers;

import com.pixellu.api.requests.Credentials;
import com.pixellu.objects.User;
import io.restassured.response.Response;

import java.util.Collections;

import static com.pixellu.helpers.RestRequest.verifyCode;

public class Requests extends RestRequest{
    private User user;

    public Requests(User user) {
        this.user = user;
    }

    public Requests() {
    }

    public Response createChecklist(User userToCreate) {
        return verifyCode(postAuth("user/create", Collections.singletonList(userToCreate), user), 200);
    }


}
