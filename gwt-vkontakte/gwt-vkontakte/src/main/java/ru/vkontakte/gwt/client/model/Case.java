package ru.vkontakte.gwt.client.model;

/**
 * падеж для склонения имени и фамилии пользователя
 */
public enum Case {
	/**
	 * именительный 
	 */
	NOM("nom"), 
	/**
	 * родительный 
	 */
	GEN("gen"), 
	/**
	 * дательный
	 */
	DAT("dat"), 
	/**
	 * винительный
	 */
	ACC("acc"), 
	/**
	 * творительный 
	 */
	INS("ins"),
	/**
	 * предложный 
	 */	
	ABL("abl");
	
	private String caseId;

	private Case(String caseId) {
		this.caseId = caseId;
	}
	
	public String getCaseId() {
		return caseId;
	}
}
