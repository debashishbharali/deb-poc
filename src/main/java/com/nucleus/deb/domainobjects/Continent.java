package com.nucleus.deb.domainobjects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONTINENT")
// @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "DebCache")
@Cacheable
public class Continent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CONTINENT_NAME", unique = true)
	private String continentName;

	@Override
	public String toString() {
		return "Continent [continentName=" + continentName + "]";
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

}
