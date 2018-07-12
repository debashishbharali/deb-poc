package com.nucleus.deb.domainobjects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
// @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "DebCache")
@Cacheable
public class City extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CITY_NAME")
	private String cityName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY")
	private Country country;

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", country=" + country + "]";
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
