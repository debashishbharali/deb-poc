package com.nucleus.deb.domainobjects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
// @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "DebCache")
@Cacheable
public class Country extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "COUNTRY_NAME", unique = true)
	private String countryName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTINENT")
	private Continent continent;

	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", continent=" + continent + "]";
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

}
