package com.exposit.datetime_test.repository;

import com.exposit.datetime_test.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long>
{

}
