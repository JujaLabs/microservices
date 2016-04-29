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

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import juja.microservices.gamification.model.entity.User;
import juja.microservices.gamification.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Rest requests to operate with users.
 *
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
@Component
@Path("/user")
public class UserControllerImpl {

    /**
     * Service.
     */
    @Autowired
    private transient Service service;

    /**
     * Create user.
     * @param name Username
     * @return Information about created user
     */
    @GET
    @Path("/create/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response createUser(@PathParam("username") final String name) {
        final String user = this.service.createUser(name);
        final Response result;
        if (user == null) {
            result = Response.status(Response.Status.BAD_REQUEST)
                .entity("can't create user.").build();
        } else {
            final String message = String.format("created with name=%s", user);
            result = Response.ok(message).build();
        }
        return result;
    }

    /**
     * Get user by uuid.
     * @param uuid Uuid
     * @return User
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{UUID}")
    public final Response getUser(@PathParam("UUID") final String uuid) {
        final User user = this.service.getUser(uuid);
        Response result;
        if (user == null) {
            result = Response.status(Response.Status.BAD_REQUEST)
                .entity(String.format("no user with %s uuid", uuid)).build();

        } else {
            result = Response.ok(user).build();
        }
        return result;
    }

    /**
     * Get list of all users.
     *
     * @return List of users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public final List<User> getUsers() {
        return this.service.getUsers();
    }

}
