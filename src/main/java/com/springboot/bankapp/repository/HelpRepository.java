package com.springboot.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bankapp.model.Help;

public interface HelpRepository extends JpaRepository<Help, Long> {

}
