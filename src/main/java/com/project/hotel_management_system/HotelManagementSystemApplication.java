package com.project.hotel_management_system;

import com.project.hotel_management_system.hotel_list.repository.HotelListCrudDao;
import com.project.hotel_management_system.hotel_list.repository.HotelEntity;
import com.project.hotel_management_system.reservation.repository.ReservationCrudDao;
import com.project.hotel_management_system.reservation.repository.ReservationEntity;
import com.project.hotel_management_system.user.account.repository.UserAccountJpaDao;
import com.project.hotel_management_system.user.account.repository.UserAccountEntity;
import com.project.hotel_management_system.utils.CountryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class HotelManagementSystemApplication implements CommandLineRunner {

	@Autowired
	UserAccountJpaDao userAccountDao;

	@Autowired
	HotelListCrudDao hotelListDao;

	@Autowired
	ReservationCrudDao reservationDao;

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userAccountDao.save(new UserAccountEntity("adrian", "test"));
		System.out.println("Login data correct: " + userAccountDao.getUserId("adrian", "test"));
		System.out.println("=======");
		userAccountDao.findAll().forEach(e -> System.out.println("ID: " + e.getId() + ", login: " + e.getNickname()));
		System.out.println("=======");

		reservationDao.save(
				new ReservationEntity("Adrian", "M", LocalDate.now(), LocalDate.now().plusDays(7)));

		hotelListDao.save(
				new HotelEntity("Hotel Karpacz", "Karpacz", CountryEnum.POLAND, "30-015 Karpacz", 269.90));
		hotelListDao.save(
				new HotelEntity("Hotel Kłaj", "Kłaj", CountryEnum.POLAND, "32-015 Kłaj", 145.00));
		hotelListDao.save(
				new HotelEntity("Hotel Szarów", "Szarów", CountryEnum.POLAND, "32-015 Kłaj", 169.90));
		hotelListDao.save(
				new HotelEntity("Burj Al Arab", "Dubaj", CountryEnum.UAE, "32-015 Kłaj", 450.00));
		hotelListDao.save(
				new HotelEntity("The Ritz-Carlton", "Nowy Jork", CountryEnum.USA, "32-015 Kłaj", 178.99));
		hotelListDao.save(
				new HotelEntity("The Savoy", "Londyn", CountryEnum.GREAT_BRITAIN, "32-015 Kłaj", 245.00));
		hotelListDao.save(
				new HotelEntity("Aman Tokyo", "Tokio", CountryEnum.JAPAN, "32-015 Kłaj", 799.00));
		hotelListDao.save(
				new HotelEntity("Hotel de Crillon", "Paryż", CountryEnum.FRANCE, "32-015 Kłaj", 650.99));
		hotelListDao.save(
				new HotelEntity("Shangri-La Hotel", "Singapur", CountryEnum.SINGAPORE, "32-015 Kłaj", 489.90));
		hotelListDao.save(
				new HotelEntity("The Oberoi Udaivilas", "Udapur", CountryEnum.INDIA, "32-015 Kłaj", 500.00));
		hotelListDao.save(
				new HotelEntity("Four Seasons Hotel", "Toronto", CountryEnum.CANADA, "32-015 Kłaj", 248.00));

	}

}
