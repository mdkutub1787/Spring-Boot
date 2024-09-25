package com.kutub.holetmanagement.repository;

import com.kutub.holetmanagement.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {


}
