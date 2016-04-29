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
package juja.microservices.gamification.model.repository;

import juja.microservices.gamification.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * User repository test.
 * @author Sergii Lisnychyi (ljore@ukr.net)
 * @version $Id$
 * @since 1.0
 */
public class UserRepositoryImplTest {

    /**
     * User repository.
     */
    @InjectMocks
    private UserRepositoryImpl repository;

    /**
     * Mongo templ.
     */
    @Mock
    private MongoTemplate templ;

    /**
     * Init mocks.
     */
    @Before
    public final void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Create user test.
     * @throws Exception if there is an issue.
     */
    @Test
    public final void createUser() throws Exception {
        final String name = "name";
        final User user = new User(name);
        final String result = this.repository.createUser(name);
        Assert.assertEquals(user.getUsername(), result);
    }

    /**
     * Get user verify.
     */
    @Test
    public final void getUserVerify() {
        final String uuid = "uuid";
        final Query query = Query.query(Criteria.where(uuid).is(uuid));
        this.repository.getUser(uuid);
        Mockito.verify(this.templ, Mockito.times(1)).findOne(query, User.class);
    }

    /**
     * Get users verify.
     */
    @Test
    public final void getUsersVerify() {
        this.repository.getUsers();
        Mockito.verify(this.templ, Mockito.times(1)).findAll(User.class);
    }

}