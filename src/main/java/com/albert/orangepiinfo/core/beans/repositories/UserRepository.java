package com.albert.orangepiinfo.core.beans.repositories;

import com.albert.orangepiinfo.core.beans.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {

    UserEntity getUserEntityByUsernameAndPassword(String username,String password);

}
