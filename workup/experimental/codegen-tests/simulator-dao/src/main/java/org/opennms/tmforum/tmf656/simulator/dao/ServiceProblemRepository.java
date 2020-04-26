package org.opennms.tmforum.tmf656.simulator.dao;

import org.opennms.tmforum.tmf656.simulator.model.ServiceProblemImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProblemRepository extends JpaRepository<ServiceProblemImpl, Long> {

}


