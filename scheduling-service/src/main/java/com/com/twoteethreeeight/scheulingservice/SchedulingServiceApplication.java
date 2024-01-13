package com.com.twoteethreeeight.scheulingservice;

import com.com.twoteethreeeight.scheulingservice.dao.AirplaneDao;
import com.com.twoteethreeeight.scheulingservice.dao.AirportDao;
import com.com.twoteethreeeight.scheulingservice.dao.FlightTimeDao;
import com.com.twoteethreeeight.scheulingservice.helpers.ScheduleHelpers;
import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import com.com.twoteethreeeight.scheulingservice.models.Runway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchedulingServiceApplication implements CommandLineRunner {
	@Autowired
	private AirportDao airportDao;
	@Autowired
	private AirplaneDao airplaneDao;
	@Autowired
	private ScheduleHelpers scheduleHelpers;
	@Autowired
	private FlightTimeDao flightTimeDao;

	public static void main(String[] args) {
		SpringApplication.run(SchedulingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<FlightTime> flightTimes = new ArrayList<>();
		flightTimes.add(new FlightTime(null, airportDao.findById("659b69c79d80da5488291852").get(), airportDao.findById("659b6a59b75559343db6867b").get(), 2F));
		flightTimes.add(new FlightTime(null, airportDao.findById("659b69c79d80da5488291852").get(), airportDao.findById("659b6ac84cec512aafec7027").get(), 4.5F));
		flightTimes.add(new FlightTime(null, airportDao.findById("659b69c79d80da5488291852").get(), airportDao.findById("659b6b0780bcc36f14293b28").get(), 6F));
		flightTimes.add(new FlightTime(null, airportDao.findById("659b6a59b75559343db6867b").get(), airportDao.findById("659b6ac84cec512aafec7027").get(), 4F));
		flightTimes.add(new FlightTime(null, airportDao.findById("659b6a59b75559343db6867b").get(), airportDao.findById("659b6b0780bcc36f14293b28").get(), 5.5F));
		flightTimes.add(new FlightTime(null, airportDao.findById("659b6ac84cec512aafec7027").get(), airportDao.findById("659b6b0780bcc36f14293b28").get(), 3F));
		flightTimeDao.saveAll(flightTimes);
	}
}
