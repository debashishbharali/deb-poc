package com.nucleus.deb.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nucleus.deb.domainobjects.City;
import com.nucleus.deb.domainobjects.Continent;
import com.nucleus.deb.domainobjects.Country;
import com.nucleus.deb.domainobjects.accounting.AccountingStructure;
import com.nucleus.deb.service.DebService;
import com.nucleus.deb.valueobjects.AccountingStructureVO;

@Controller
@RequestMapping("/deb")
public class DebController {

	private static final String MESSAGE = "message";
	private static final String MESSAGE1 = "message1";
	private static final String MESSAGE2 = "message2";
	private static final String MESSAGE3 = "message3";
	private static final String MESSAGE4 = "message4";

	private static final String HELLO = "hello";
	private static final String HELLO2 = "hello2";
	private static final String ACCOUNTING_STRUCTURE = "accountingStructure";
	private static final String ACCOUNTING_STRUCTURE_FORM = "accountingStructureForm";

	private static final String HELLO_SPRING_MVC_FRAMEWORK = "Hello Spring MVC Framework!";
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";

	@Inject
	@Named("debService")
	private DebService debService;

	public DebService getDebService() {
		return debService;
	}

	public void setDebService(DebService debService) {
		this.debService = debService;
	}

	@RequestMapping(path = "/createContinent", method = RequestMethod.GET)
	public String createContinent(ModelMap model, @RequestParam(value = "name", required = true) String name) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, name);

		Continent continent = debService.createContinent(name);
		if (continent != null) {
			model.addAttribute(MESSAGE2, SUCCESS);
		} else {
			model.addAttribute(MESSAGE2, FAILED);
			model.addAttribute(MESSAGE3, "Continent creation failed internally");
		}

		return HELLO;
	}

	@RequestMapping(path = "/createCity", method = RequestMethod.GET)
	public String createCity(ModelMap model, @RequestParam(value = "cityName", required = true) String cityName,
			@RequestParam(value = "countryName", required = true) String countryName) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, cityName);

		Country country = debService.getCountryByName(countryName);
		if (country != null) {
			City city = debService.createCity(cityName, country);
			if (city != null) {
				model.addAttribute(MESSAGE2, SUCCESS);
			} else {
				model.addAttribute(MESSAGE2, FAILED);
				model.addAttribute(MESSAGE3, "City creation failed internally");
			}
		} else {
			model.addAttribute(MESSAGE2, FAILED);
			model.addAttribute(MESSAGE3, "Country doesn't exist");
		}

		return HELLO;
	}

	@RequestMapping(path = "/createCountry", method = RequestMethod.GET)
	public String createCountry(ModelMap model,
			@RequestParam(value = "countryName", required = true) String countryName,
			@RequestParam(value = "continentName", required = true) String continentName) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, countryName);

		Continent continent = debService.getContinentByName(continentName);
		if (continent != null) {
			Country country = debService.createCountry(countryName, continent);
			if (country != null) {
				model.addAttribute(MESSAGE2, SUCCESS);
			} else {
				model.addAttribute(MESSAGE2, FAILED);
				model.addAttribute(MESSAGE3, "Country creation failed internally");
			}

		} else {
			model.addAttribute(MESSAGE2, FAILED);
			model.addAttribute(MESSAGE3, "Continent doesn't exist");
		}

		return HELLO;
	}

	@RequestMapping(path = "/getCityByName", method = RequestMethod.GET)
	public String getCityByName(ModelMap model, @RequestParam(value = "cityName", required = true) String cityName) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, cityName);

		City city = debService.getCityByName(cityName);
		if (city != null) {
			model.addAttribute(MESSAGE2, city.getId());
			model.addAttribute(MESSAGE3, city.toString());
		}

		return HELLO2;
	}

	@RequestMapping(path = "/getCountryByName", method = RequestMethod.GET)
	public String getCountryByName(ModelMap model,
			@RequestParam(value = "countryName", required = true) String countryName) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, countryName);

		Country country = debService.getCountryByName(countryName);
		if (country != null) {
			model.addAttribute(MESSAGE2, country.getId());
			model.addAttribute(MESSAGE3, country.toString());
		}

		return HELLO2;
	}

	@RequestMapping(path = "/getContinentByName", method = RequestMethod.GET)
	public String getContinentByName(ModelMap model,
			@RequestParam(value = "continentName", required = true) String continentName) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, continentName);

		Continent continent = debService.getContinentByName(continentName);
		if (continent != null) {
			model.addAttribute(MESSAGE2, continent.getId());
			model.addAttribute(MESSAGE3, continent.toString());
		}

		return HELLO2;
	}
	
	
	@RequestMapping(path = "/getContinentById", method = RequestMethod.GET)
	public String getContinentById(ModelMap model,
			@RequestParam(value = "id", required = true) Integer id) {

		model.addAttribute(MESSAGE, HELLO_SPRING_MVC_FRAMEWORK);
		model.addAttribute(MESSAGE1, id);

		Continent continent = debService.getContinentById(id);
		if (continent != null) {
			model.addAttribute(MESSAGE2, continent.getId());
			model.addAttribute(MESSAGE3, continent.toString());
		}

		return HELLO2;
	}
	

	@RequestMapping(path = "/getAccountingStructure", method = RequestMethod.GET)
	public String getAccountingStructure(ModelMap model) {

		Long tenantId = debService.getRandomTenantId();
		Long productId = debService.getRandomProductId();
		Long schemeId = debService.getRandomSchemeId(productId);
		Long currencyId = debService.getRandomCurrencyId();
		Long accountingClassificationMstId = debService.getRandomAccClassMstId();
		String transactionType = debService.getRandomTransactionType();

		List<AccountingStructure> accountingStructure = debService.getAccountingStructureByAllAttribtes(tenantId,
				productId, schemeId, currencyId, accountingClassificationMstId, transactionType);

		if (accountingStructure != null && !accountingStructure.isEmpty()) {
			model.addAttribute(MESSAGE, "Accounting Structure Get : Successful");
		} else {
			model.addAttribute(MESSAGE, "Accounting Structure Get : Failed");
		}

		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/getAccountingStructureFromCacheForm", method = RequestMethod.GET)
	public ModelAndView getAccountingStructureFromCacheGet(ModelMap model) {
		return new ModelAndView(ACCOUNTING_STRUCTURE_FORM, "accountingStructure", new AccountingStructureVO());
	}

	@RequestMapping(path = "/getAccountingStructureFromCache", method = RequestMethod.POST)
	public String getAccountingStructureFromCachePost(ModelMap model,
			@ModelAttribute("accountingStructure") AccountingStructureVO accountingStructureVO) {

		Long tenantId = accountingStructureVO.getTenantId();
		Long productId = accountingStructureVO.getProductId();
		Long schemeId = accountingStructureVO.getSchemeId();
		Long currencyId = accountingStructureVO.getCurrencyId();
		Long accountingClassificationMstId = accountingStructureVO.getAccountingClassificationMstId();
		String transactionType = accountingStructureVO.getTransactionType();

		List<AccountingStructure> accountingStructure = debService.getAccountingStructureFromCache(tenantId,
				productId, schemeId, currencyId, accountingClassificationMstId, transactionType);

		if (accountingStructure != null && !accountingStructure.isEmpty()) {
			model.addAttribute(MESSAGE, "Accounting Structure Get : Successful");
		} else {
			model.addAttribute(MESSAGE, "Accounting Structure Get : Failed");
		}

		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/getAccountingStructureFromCache", method = RequestMethod.GET)
	public String getAccountingStructureFromCache(ModelMap model) {

		Long tenantId = debService.getRandomTenantId();
		Long productId = debService.getRandomProductId();
		Long schemeId = debService.getRandomSchemeId(productId);
		Long currencyId = debService.getRandomCurrencyId();
		Long accountingClassificationMstId = debService.getRandomAccClassMstId();
		String transactionType = debService.getRandomTransactionType();

		List<AccountingStructure> accountingStructure = debService.getAccountingStructureFromCache(tenantId, productId,
				schemeId, currencyId, accountingClassificationMstId, transactionType);

		if (accountingStructure != null && !accountingStructure.isEmpty()) {
			model.addAttribute(MESSAGE, "Accounting Structure Get From Cache : Successful");
		} else {
			model.addAttribute(MESSAGE, "Accounting Structure Get From Cache : Failed");
		}

		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/populateAccountingCache", method = RequestMethod.GET)
	public String populateAccountingCache(ModelMap model) {

		debService.putAllAccountingStructuresIntoCache();
		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/performTestAccounting", method = RequestMethod.GET)
	public String performTestAccounting(ModelMap model,
			@RequestParam(value = "threadCount", required = true) int threadCount) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(threadCount);
		IntStream.range(0, threadCount)
				.forEach(x -> newFixedThreadPool.submit(debService::loopThroughAllAccountingStructures));

		model.addAttribute(MESSAGE, "performTestAccounting : completed");
		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/performTestAccountingCache", method = RequestMethod.GET)
	public String performTestAccountingCache(ModelMap model,
			@RequestParam(value = "threadCount", required = true) int threadCount) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(threadCount);
		IntStream.range(0, threadCount)
				.forEach(x -> newFixedThreadPool.submit(debService::loopThroughAllAccountingStructuresFromCache));

		model.addAttribute(MESSAGE, "performTestAccountingCache : completed");
		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/heavyPerformTestAccounting", method = RequestMethod.GET)
	public String heavyPerformTestAccounting(ModelMap model,
			@RequestParam(value = "threadCount", required = true) int threadCount,
			@RequestParam(value = "hits", required = true) int hits) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(threadCount);
		IntStream.range(0, hits).parallel()
				.forEach(x -> newFixedThreadPool.submit(this::randomGetOfAccountingStructure));
		model.addAttribute(MESSAGE, "heavyPerformTestAccounting : completed");
		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/heavyPerformTestAccountingCache", method = RequestMethod.GET)
	public String heavyPerformTestAccountingCache(ModelMap model,
			@RequestParam(value = "threadCount", required = true) int threadCount,
			@RequestParam(value = "hits", required = true) int hits) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(threadCount);
		IntStream.range(0, hits).parallel()
				.forEach(x -> newFixedThreadPool.submit(this::randomGetOfAccountingStructureFromCache));
		model.addAttribute(MESSAGE, "heavyPerformTestAccountingCache : completed");
		return ACCOUNTING_STRUCTURE;
	}

	@RequestMapping(path = "/printTimeAndCount", method = RequestMethod.GET)
	public String printTimeAndCount(ModelMap model) {

		Map<String, Long> printTotalTimeTakenAndCount = debService.printTotalTimeTakenAndCount();
		model.addAttribute(MESSAGE, "Time and Count are printed to Console Logs");
		model.addAttribute(MESSAGE1, "Time Taken : " + printTotalTimeTakenAndCount.get("TIME"));
		model.addAttribute(MESSAGE2, "Count : " + printTotalTimeTakenAndCount.get("COUNT"));
		model.addAttribute(MESSAGE3, "Time Taken Cache : " + printTotalTimeTakenAndCount.get("TIME_CACHE"));
		model.addAttribute(MESSAGE4, "Count Cache : " + printTotalTimeTakenAndCount.get("COUNT_CACHE"));

		return ACCOUNTING_STRUCTURE;
	}

	private void randomGetOfAccountingStructure() {
		Long tenantId = debService.getRandomTenantId();
		Long productId = debService.getRandomProductId();
		Long schemeId = debService.getRandomSchemeId(productId);
		Long currencyId = debService.getRandomCurrencyId();
		Long accountingClassificationMstId = debService.getRandomAccClassMstId();
		String transactionType = debService.getRandomTransactionType();

		debService.getAccountingStructureByAllAttribtes(tenantId, productId, schemeId, currencyId,
				accountingClassificationMstId, transactionType);
	}

	private void randomGetOfAccountingStructureFromCache() {
		Long tenantId = debService.getRandomTenantId();
		Long productId = debService.getRandomProductId();
		Long schemeId = debService.getRandomSchemeId(productId);
		Long currencyId = debService.getRandomCurrencyId();
		Long accountingClassificationMstId = debService.getRandomAccClassMstId();
		String transactionType = debService.getRandomTransactionType();

		debService.getAccountingStructureFromCache(tenantId, productId, schemeId, currencyId,
				accountingClassificationMstId, transactionType);
	}

}