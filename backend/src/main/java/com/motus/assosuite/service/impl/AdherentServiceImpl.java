package com.motus.assosuite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.motus.assosuite.api.exceptions.BusinessException;
import com.motus.assosuite.enums.AssosBusinessErrorCode;
import com.motus.assosuite.models.Adherent;
import com.motus.assosuite.repository.AdherentRepository;
import com.motus.assosuite.service.AdherentService;

@Service
public class AdherentServiceImpl implements AdherentService {

	private final AdherentRepository repository;

	@Autowired
	public AdherentServiceImpl(AdherentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Adherent create(Adherent adherent) {
		return repository.save(adherent);
	}

	@Override
	public List<Adherent> findAll(Integer pageNum, Integer pageSize) {
		if (!(pageNum == null || pageSize == null)) {
			Pageable paging = PageRequest.of(pageNum, pageSize);
			return repository.findAll(paging).toList();
		}
		return repository.findAll();
	}

	@Override
	public Adherent update(Adherent adherent, String uuid) {
		Adherent adherentIn = repository.findByUuid(uuid);
		if (adherentIn == null) {
			throw new BusinessException("Adherent not found in DB", AssosBusinessErrorCode.ADHERENT_NOT_FOUND);
		}
		adherentIn.setFirstName(adherent.getFirstName());
		adherentIn.setLastName(adherent.getLastName());
		adherentIn.setMail(adherent.getMail());
		adherentIn.setPhone(adherent.getPhone());
		adherentIn.setAddress(adherent.getAddress());
		adherentIn.setBirthDay(adherent.getBirthDay());
		adherentIn.setFamilialSituation(adherent.getFamilialSituation());
		adherentIn.setGenderType(adherent.getGenderType());
		return repository.save(adherent);
	}

	@Override
	public Adherent find(String uuid) {
		Adherent adherent = repository.findByUuid(uuid);
		if (adherent == null) {
			throw new BusinessException("Adherent not found in DB", AssosBusinessErrorCode.ADHERENT_NOT_FOUND);
		}
		return adherent;
	}
	
	@Override
	public void delete(String uuid) {
		Adherent adherent = repository.findByUuid(uuid);
		if (adherent == null) {
			throw new BusinessException("Adherent not found in DB", AssosBusinessErrorCode.ADHERENT_NOT_FOUND);
		}
		repository.delete(adherent);
	}
	
	

}
