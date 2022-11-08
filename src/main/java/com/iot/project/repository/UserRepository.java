package com.iot.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.iot.project.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   Optional<User> findByEmail(String email); 

   List<User> findByNameStartingWith(String name);

   @Procedure("add_user_without_name")
   void saveUserWithGeneratedName(String email);

   @Procedure("fill_user_table")
   void pasteGenratedData();

   @Procedure("create_many_tables")
   void generateDummyTablesUsingUserNames();
}
