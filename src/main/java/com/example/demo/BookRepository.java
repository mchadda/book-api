
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Spring Data JPA creates CRUD implementation at runtime automatically.

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
