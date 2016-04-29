/**
 * Copyright (c) 2016, juja.com.ua
 * All rights reserved.
 * <p/>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p/>
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * <p/>
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <p/>
 * * Neither the name of microservices nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * <p/>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package juja.microservices.gamification.jersey;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import juja.microservices.gamification.model.entity.User;
import juja.microservices.gamification.service.Service;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * User controller test.
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
public class UserControllerImplTest {

    /**
     * User controller.
     */
    @InjectMocks
    private transient UserControllerImpl controller;

    /**
     * User service.
     */
    @Mock
    private transient Service service;

    /**
     * Init mocks.
     */
    @BeforeMethod
    public final void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Get user with wrong uuid.
     * @throws Exception if there is an issue.
     */
    @Test
    public final void getUserWithWrongUuid() throws Exception {
        final String uuid = "1234";
        Mockito.when(this.service.getUser(uuid)).thenReturn(null);
        final Response resp = this.controller.getUser(uuid);
        final Response expected = Response.status(Response.Status.BAD_REQUEST)
            .entity(String.format("no user with %s uuid", uuid)).build();
        Assert.assertEquals(expected.getStatus(), resp.getStatus());
        Assert.assertEquals(expected.getEntity(), resp.getEntity());
    }

    /**
     * Get user with correct uuid.
     * @throws Exception if there is an issue.
     */
    @Test
    public final void getUserWithCorrectUuid() throws Exception {
        final String uuid = "12345";
        final User user = new User();
        user.setUuid(uuid);
        Mockito.when(this.service.getUser(uuid)).thenReturn(user);
        final Response resp = this.controller.getUser(uuid);
        final Response expected = Response.ok(user).build();
        Assert.assertEquals(expected.getStatus(), resp.getStatus());
        Assert.assertEquals(expected.getEntity(), resp.getEntity());
    }

    /**
     * Verify create user call.
     */
    @Test
    public final void createUserVerify() {
        final String user = "user{name:name}";
        final String name = "name";
        Mockito.when(this.service.createUser(name)).thenReturn(user);
        this.controller.createUser(name);
        Mockito.verify(this.service, Mockito.times(1)).createUser(name);
    }

    /**
     * Verify create user call.
     */
    @Test
    public final void getUsersVerify() {
        final List<User> users = Collections.singletonList(new User());
        Mockito.when(this.service.getUsers()).thenReturn(users);
        final List<User> result = this.controller.getUsers();
        Mockito.verify(this.service, Mockito.times(1)).getUsers();
        Assert.assertEquals(users, result);
    }

}