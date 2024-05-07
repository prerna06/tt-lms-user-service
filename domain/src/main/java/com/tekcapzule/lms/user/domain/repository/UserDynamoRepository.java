package com.tekcapzule.lms.user.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.lms.user.domain.model.User;


public interface UserDynamoRepository extends CrudRepository<User, String> {
    int getAllUsersCount();

}
