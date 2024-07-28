package com.optd.repository;

import com.optd.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Short> {

    @Query("select ds from DeliveryStatus ds where ds.statusName =:statusName")
    DeliveryStatus findByStatusName(@Param("statusName") String statusName);
}
