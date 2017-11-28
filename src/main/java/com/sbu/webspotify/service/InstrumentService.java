package com.sbu.webspotify.service;

import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
import com.sbu.webspotify.model.Instrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.repo.InstrumentRepository;

import java.util.HashSet;
import java.util.Set;

@Service("instrumentService")
public class InstrumentService {

	@Autowired
	private InstrumentRepository instrumentRepository;

	public Instrument getInstrumentById(int id) {
        return instrumentRepository.findById(id);
    }

    public Set<Instrument> findAll() {
        return new HashSet<Instrument>(instrumentRepository.findAll());
    }

	public Set<InstrumentIdentifier> searchByName(String query) {
		return instrumentRepository.findByNameContaining(query);
	}

}
