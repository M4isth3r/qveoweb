package com.qveo.qveoweb.service.Imp;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qveo.qveoweb.dao.SerieDao;
import com.qveo.qveoweb.model.Genero;
import com.qveo.qveoweb.model.Plataforma;
import com.qveo.qveoweb.model.Serie;
import com.qveo.qveoweb.service.SerieService;

@Service
public class SerieServiceImp implements SerieService {

	@Autowired
	SerieDao serieDao;

	@Override
	@Transactional(readOnly = true)
	public List<Serie> findAllSerie() {
		// TODO Auto-generated method stub
		return serieDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Serie> getSerie(Integer id) {

		return serieDao.findById(id);
	}

	@Override
	@Transactional
	public void save(Serie serieNew) {
		serieDao.save(serieNew);
	}

	@Override
	@Transactional
	public void deleteSerie(Integer id) {
		serieDao.deleteById(id);

	}

	public List<Serie> buscarSerie(Collection<Genero> generos) {
		List<Serie> serie = serieDao.findByGenerosIn(generos);
		return serie;
	}

	public List<Integer> buscarAllYears() {
		List<Serie> serieAll = serieDao.findAll();

		ArrayList<Integer> fecha = new ArrayList<Integer>();

		serieAll.forEach(serieR -> fecha.add(getYear(serieR.getFechaInicio())));
		fecha.stream().distinct().collect(Collectors.toList());

		List<Integer> listaLimpia = new ArrayList<Integer>(new HashSet<>(fecha));
		Collections.sort(listaLimpia);

		return listaLimpia;
	}

	public int getYear(Date date2) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date2);

		int year = cal.get(Calendar.YEAR);

		return year;
	}

	@Override
	public List<Serie> buscarPorYear(String[] years) {
		List<Serie> seriefiltrada = new ArrayList<Serie>();

		Date fechaInicio = null;
		Date fechafinal = null;

		if (years.length == 1) {

			fechaInicio = fecha(years[0], 1);
			fechafinal = fecha(years[0], 2);

			seriefiltrada = serieDao.findByFechaInicioBetween(fechaInicio, fechafinal);
		}

		if (years.length >= 2) {
			for (String year : years) {

				fechaInicio = fecha(year, 1);
				fechafinal = fecha(year, 2);

				seriefiltrada.addAll(serieDao.findByFechaInicioBetween(fechaInicio, fechafinal));
			}
		}

		return seriefiltrada;
	}

	@Override
	public List<Serie> buscarPlataforma(Collection<Plataforma> plataforma) {

		List<Serie> serie = new ArrayList<Serie>(new HashSet<Serie>());

		serie = serieDao.findByPlataformasIn(plataforma);

		return serie.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Serie> buscarGeneroPlataforma(Collection<Plataforma> plataforma, Collection<Genero> generos) {
		List<Serie> serie = new ArrayList<Serie>(new HashSet<Serie>());
		
		serie = serieDao.findByPlataformasInAndGenerosIn(plataforma, generos);
		
		return serie.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Serie> buscarPlataformaYears(Collection<Plataforma> plataforma, String[] years) {
		List<Serie> serieFiltra = new ArrayList<Serie>(new HashSet<Serie>());

		Date fechaInicio = null;
		Date fechafinal = null;

		if (years.length == 1) {

			fechaInicio = fecha(years[0], 1);
			fechafinal = fecha(years[0], 2);

			serieFiltra.addAll(serieDao.findByPlataformasInAndFechaInicioBetween(plataforma, fechaInicio, fechafinal));
		}

		if (years.length >= 2) {

			for (String year : years) {
				fechaInicio = fecha(year, 1);
				fechafinal = fecha(year, 2);

				serieFiltra
						.addAll(serieDao.findByPlataformasInAndFechaInicioBetween(plataforma, fechaInicio, fechafinal));
			}

		}
		/*
		 * List<Serie> borrador = serieFiltra; serieFiltra = null;
		 * borrador.stream().distinct().collect(Collectors.toList()); serieFiltra = new
		 * ArrayList<Serie>(new HashSet<>(borrador));
		 */

		return serieFiltra.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Serie> busquedaGeneroYears(String[] years, Collection<Genero> generos) {
		List<Serie> serieFiltra = new ArrayList<Serie>(new HashSet<Serie>());

		Date fechaInicio = null;
		Date fechafinal = null;
		if (years.length == 1) {
			fechaInicio = fecha(years[0], 1);
			fechafinal = fecha(years[0], 2);
			serieFiltra.addAll(serieDao.findByGenerosInAndFechaInicioBetween(generos, fechaInicio, fechafinal));
		}

		if (years.length >= 2) {

			for (String year : years) {
				fechaInicio = fecha(year, 1);
				fechafinal = fecha(year, 2);
				serieFiltra.addAll(serieDao.findByGenerosInAndFechaInicioBetween(generos, fechaInicio, fechafinal));
			}

		}
		/*
		 * List<Serie> borrador = serieFiltra; serieFiltra = null;
		 * borrador.stream().distinct().collect(Collectors.toList()); serieFiltra = new
		 * ArrayList<Serie>(new HashSet<>(borrador));
		 */

		return serieFiltra.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Serie> busquedaCompletaSerie(Collection<Genero> generos, Collection<Plataforma> plataforma,
			String[] years) {
		List<Serie> serieFiltra = new ArrayList<Serie>(new HashSet<Serie>());

		Date fechaInicio = null;
		Date fechafinal = null;

		if (years.length == 1) {
			fechaInicio = fecha(years[0], 1);
			fechafinal = fecha(years[0], 2);
			serieFiltra.addAll(serieDao.findByPlataformasInAndGenerosInAndFechaInicioBetween(plataforma, generos,
					fechaInicio, fechafinal));
		}

		if (years.length >= 2) {

			for (String year : years) {
				fechaInicio = fecha(year, 1);
				fechafinal = fecha(year, 2);
				serieFiltra.addAll(serieDao.findByPlataformasInAndGenerosInAndFechaInicioBetween(plataforma, generos,
						fechaInicio, fechafinal));
			}

		}

		/*
		 * List<Serie> borrador = serieFiltra; serieFiltra = null;
		 * borrador.stream().distinct().collect(Collectors.toList()); serieFiltra = new
		 * ArrayList<Serie>(new HashSet<>(borrador));
		 */

		return serieFiltra.stream().distinct().collect(Collectors.toList());
	}

	public Date fecha(String year, int accion) {
		Date fecha = null;

		switch (accion) {
		case 1:
			fecha = Date.valueOf(year + "-1-1");
			break;

		case 2:
			fecha = Date.valueOf(year + "-12-31");
			break;
		}

		return fecha;
	}
}