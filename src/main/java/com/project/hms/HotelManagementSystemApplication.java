package com.project.hms;

import com.project.hms.common.CountryEnum;
import com.project.hms.modules.hotel_list.repository.HotelListCrudDao;
import com.project.hms.modules.hotel_list.entity.HotelEntity;
import com.project.hms.modules.reservation.repository.ReservationCrudDao;
import com.project.hms.modules.reservation.entity.ReservationEntity;
import com.project.hms.modules.account.repository.UserAccountJpaDao;
import com.project.hms.modules.account.entity.UserAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class HotelManagementSystemApplication implements CommandLineRunner {

	@Autowired
	UserAccountJpaDao userAccountDao;

	@Autowired
	HotelListCrudDao hotelListDao;

	@Autowired
	ReservationCrudDao reservationDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String encodedPass = passwordEncoder.encode("test");
		userAccountDao.save(new UserAccountEntity("adrian", encodedPass));
		System.out.println("=======");
		userAccountDao.findAll().forEach(e -> System.out.println("ID: " + e.getId() + ", username: " + e.getUsername()));
		System.out.println("=======");

		reservationDao.save(
				new ReservationEntity("Adrian", "M", LocalDate.now(), LocalDate.now().plusDays(7)));
		hotelListDao.save(
				new HotelEntity("Hotel Karpacz", "Karpacz", CountryEnum.POLAND, "30-015 Karpacz", 269.90));
		hotelListDao.save(
				new HotelEntity("Hotel Kraków", "Kraków", CountryEnum.POLAND, "32-010 Kraków", 145.00));
		hotelListDao.save(
				new HotelEntity("Hotel Nowy Sącz", "Nowy Sącz", CountryEnum.POLAND, "30-028 Nowy Sącz", 169.90));
		hotelListDao.save(
				new HotelEntity("Burj Al Arab", "Dubaj", CountryEnum.UAE, "22-001 Warszawa", 450.00));
		hotelListDao.save(
				new HotelEntity("The Ritz-Carlton", "Nowy Jork", CountryEnum.USA, "22-001 Warszawa", 178.99));
		hotelListDao.save(
				new HotelEntity("The Savoy", "Londyn", CountryEnum.GREAT_BRITAIN, "22-001 Warszawa", 245.00));
		hotelListDao.save(
				new HotelEntity("Aman Tokyo", "Tokio", CountryEnum.JAPAN, "22-001 Warszawa", 799.00));
		hotelListDao.save(
				new HotelEntity("Hotel de Crillon", "Paryż", CountryEnum.FRANCE, "22-001 Warszawa", 650.99));
		hotelListDao.save(
				new HotelEntity("Shangri-La Hotel", "Singapur", CountryEnum.SINGAPORE, "22-001 Warszawa", 489.90));
		hotelListDao.save(
				new HotelEntity("The Oberoi Udaivilas", "Udapur", CountryEnum.INDIA, "22-001 Warszawa", 500.00));
		hotelListDao.save(
				new HotelEntity("Four Seasons Hotel", "Toronto", CountryEnum.CANADA, "22-001 Warszawa", 248.00));

	}
}
