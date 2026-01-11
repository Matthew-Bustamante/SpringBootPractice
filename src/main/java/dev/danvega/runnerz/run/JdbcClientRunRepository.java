package dev.danvega.runnerz.run;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

@Repository
public class JdbcClientRunRepository {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
	private final JdbcClient jdbcClient;
	
	public JdbcClientRunRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}
	
	/**
	 * findAll() Method that returns all runs present in the database
	 * @return a list of runs
	 */
	public List<Run> findAll(){
		return jdbcClient.sql("select * from run")
				.query(Run.class) //executes the SQL statement and converts the data into a Run
				.list(); //returns a list of runs
	}
	/**
	 * findById method: this method returns a run by taking in an id
	 * @param 
	 * id of the run(Integer)
	 * @return
	 * an optional 
	 */
	public Optional<Run> findById(Integer id){
		return jdbcClient.sql("SELECT id, title, started_on, completed_on, location FROM run WHERE id = :id")
				.param("id", id) // passes the id into the SQL statement param
				.query(Run.class)// executes the SQL statement and converts the data into an instance of Run
				.optional(); // returns an optional
	}
	
	public void create(Run run) {
		var updated = jdbcClient.sql("INSERT INTO  run(id, title, started_on, completed_on, miles, location) values(?,?,?,?,?,?)")
				.params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
				.update();
		
		Assert.state(updated == 1, "Failed to create run" + run.title());
	}
	
	public void update(Run run, Integer id) {
		var updated = jdbcClient.sql("UPDATE run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
				.params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location(), id))
				.update();
		
		Assert.state(updated == 1, "Failed to update Run" + run.title());
	}
	
	public void delete(Integer id) {
		var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id")
				.param("id", id)
				.update();
		
		Assert.state(updated == 1, "Failed to delete run" + id);
	}
	
	public int count() {
		return jdbcClient.sql("SELECT * FROM run")
				.query()
				.listOfRows()
				.size();
	}
	
	public void saveAll(List<Run> runs) {
		runs.stream().forEach(this::create);
	}
	
	public List<Run> findByLocation(String location){
		return jdbcClient.sql("SELECT * FROM run WHERE location = :location")
				.param("location", location)
				.query(Run.class)
				.list();
	}
	
}
