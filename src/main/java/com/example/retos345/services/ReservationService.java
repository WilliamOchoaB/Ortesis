package com.example.retos345.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retos345.entities.Reservation;
import com.example.retos345.repositories.ReservationRepository;


@Service
public class ReservationService {
    
        @Autowired
        private ReservationRepository reservationRepository;

        public ReservationService(ReservationRepository reservationRepository) {
            this.reservationRepository = reservationRepository;
        }

        // ****** INICIO REPORTES ******
        public List<Reservation> getReservationsBetweenTime(Date start, Date end){
            return this.reservationRepository.findByStartDateBetween(start, end);
        }

        public String getReservationsStatus(){
            List<Reservation> completed = this.reservationRepository.findByStatus("completed");
            List<Reservation> cancelled = this.reservationRepository.findByStatus("cancelled");
            System.out.println("**** Completed: "+ completed.size());
            System.out.println("**** Cancelled: "+ cancelled.size());
            return "{'completed':"+completed.size()+",'cancelled':"+cancelled.size()+" }";
        }

        public List<Reservation> getReservationsClients(){
            return this.reservationRepository.findAll();
        }


        // ****** FIN REPORTES ******

        // METODOS CRUD
        public List<Reservation> getListReservations(){
            return this.reservationRepository.findAll();
        }

        public Reservation getReservation(int id){
            if(!this.reservationRepository.findById(id).isEmpty()){
                return this.reservationRepository.findById(id).get();
            }else{
                return null;
            }
        }

        public Reservation crearReservation(Reservation reservation){
            return this.reservationRepository.save(reservation);
        }

        public void eliminarReservation(int id){
            if(!this.reservationRepository.findById(id).isEmpty()){
                this.reservationRepository.deleteById(id);
            }
        }

        public void actualizarReservation(int id, Reservation reservation){
            if(!this.reservationRepository.findById(id).isEmpty()){
                Reservation reservationDB = this.reservationRepository.findById(id).get();

                this.reservationRepository.save(reservationDB);
            }
        }
}
