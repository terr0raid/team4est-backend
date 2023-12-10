package com.team4est.userservice.repository;

import com.team4est.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
  @Query(
    value = """
    {
      $or: [
        { _id: ?0 },
        { username: ?0 },
        { email: ?0 }
      ]
    }
    """
  )
  User findByIdOrUsernameOrEmail(String searchParam);
}
