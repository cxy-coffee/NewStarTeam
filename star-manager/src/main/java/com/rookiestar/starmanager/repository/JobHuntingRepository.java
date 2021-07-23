package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.employee.JobHunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chris
 * @date 2021/7/21
 * @time 9:11
 */
public interface JobHuntingRepository extends JpaRepository<JobHunting,Long> {
    /**
     * find a JobHunting object in the database who's now looking for a job by his account number
     * @param accountNumber the account number of the jobHunting object to find
     * @return the JobHunting object which matches the requirement
     */
    @Query("select jht from JobHunting jht where jht.accountNumber=?1 and jht.jobHunting=true ")
    JobHunting findCurrentJobHuntingByAccountNumber(int accountNumber);

    /**
     * find JobHunting objects in the database who's now looking for a job by his ideal position
     * @param idealPosition the ideal position of the jobHunting objects to find
     * @return the JobHunting objects which match the requirement
     */
    @Query("select jht from JobHunting jht where jht.idealPosition=?1 and jht.jobHunting=true")
    List<JobHunting> findCurrentJobHuntingsByIdealPosition(String idealPosition);

    /**
     * find JobHunting objects in the database who's now looking for a job by his ideal position and degree
     * @param idealPosition the ideal position of the jobHunting objects to find
     * @param degree the degree of the jobHunting objects to find
     * @return the JobHunting objects which match the requirement
     */
    @Query("select jht from JobHunting jht where jht.idealPosition=?1 and jht.degree=?2 and jht.jobHunting=true")
    List<JobHunting> findCurrentJobHuntingsByIdealPositionAndDegree(String idealPosition,String degree);

    /**
     * find JobHunting objects in the database who's now looking for a job by his degree
     * @param degree the degree of the jobHunting objects to find
     * @return the JobHunting objects which match the requirement
     */
    @Query("select jht from JobHunting jht where jht.degree=?1 and jht.jobHunting=true")
    List<JobHunting> findCurrentJobHuntingsByDegree(String degree);

    /**
     * find a JobHunting object, no matter he's looking for a job or not, by his account number
     * @param accountNumber the account number of the jobHunting object to find
     * @return the JobHunting object which matches the requirement
     */
    JobHunting findJobHuntingByAccountNumber(int accountNumber);

    /**
     * find all jobHuntings that are now looking for a job
     * @return all joHuntings looking for a job
     */
    @Query("select jht from JobHunting jht where jht.jobHunting=true")
    List<JobHunting> findAllCurrentJobHunting();

    /**
     * update a jobHunting with the certain accountNumber
     * @param degree the expected degree
     * @param idealPosition the expected idealPosition
     * @param jobHunting the expected jobHunting state
     * @param accountNumber the accountNumber to locate the jobHunting
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update JobHunting set degree = ?1 , idealPosition=?2, jobHunting=?3 where accountNumber=?4")
    void updateJobHunting(String degree, String idealPosition, boolean jobHunting,int accountNumber);
}
