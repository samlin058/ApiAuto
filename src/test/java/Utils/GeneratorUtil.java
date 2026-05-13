package Utils;

/**
 * Êı¾İÉú³ÉÆ÷
 * @Description  TODO 
 * @author       luandao
 * @Date         2021Äê12ÔÂ23ÈÕ 
 *
 */
public class GeneratorUtil {
	
	private static String[] mobileHead = ("130,131,132,133,134,135,136,137,138,139,150,151,152,153,155,156,157,158,159,180,181,182,183,184,185,186,187,188").split(",");
    
	
	private static String[] bank = { "ÖĞ¹úÒøĞĞ","ÓÊÕş´¢Ğî","ÕĞÉÌÒøĞĞ","Å©ÒµÒøĞĞ","¹¤ÉÌÒøĞĞ","½¨ÉèÒøĞĞ","½»Í¨ÒøĞĞ"};
	
	
	private static String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35",
			"36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65",
			"71", "81", "82" };

	
	/**
	 * Éú³É0~size-1µÄËæ»úÊı
	 * @param size
	 * @return int
	 *
	 * @author       luandao
	 * @Date         2021Äê12ÔÂ23ÈÕ 
	 *
	 */
	public static int getRandomNum(int size) {
		return (int) (Math.random() * size); 
	}
    
	/**
	 * Ëæ»úÉú³ÉÊÖ»úºÅ
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2021Äê12ÔÂ23ÈÕ 
	 *
	 */
	public static String mobileGenerator() {
		int size = mobileHead.length;
		StringBuilder mobile = new StringBuilder(mobileHead[getRandomNum(size)]);
		for (int i = 4; i <= 11; i++) {
			mobile.append(getRandomNum(10));
		}
		return mobile.toString();
	}
    
	/**
	 * Ëæ»úÉú³ÉÃû×Ö
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2021Äê12ÔÂ23ÈÕ 
	 *
	 */
	public static String nameGenerator() {
		String lastName = "ÕÔÇ®ËïÀîÖÜÎâÖ£Íõ·ë³ÂñÒÎÀ½¯Éòº«ÑîÖìÇØÓÈĞíºÎÂÀÊ©ÕÅ¿×²ÜÑÏ»ª½ğÎºÌÕ½ªÆİĞ»×ŞÓ÷°ØË®ñ¼ÕÂÔÆËÕÅË"
				+ "¸ğŞÉ·¶ÅíÀÉÂ³Î¤²ıÂíÃç·ï»¨·½ÓáÈÎÔ¬ÁøÛº±«Ê·ÌÆ·ÑÁ®á¯Ñ¦À×ºØÄßÌÀëøÒóÂŞ±ÏºÂÚù°²³£ÀÖÓÚÊ±¸µÆ¤±åÆë¿µÎéÓàÔª²·¹ËÃÏÆ½»ÆºÍÄÂÏôÒü"
				+ "Ò¦ÉÛÕ¿ÍôÆîÃ«ÓíµÒÃ×±´Ã÷ê°¼Æ·ü³É´÷Ì¸ËÎÃ©ÅÓĞÜ¼ÍÊæÇüÏî×£¶­Áº¶ÅÈîÀ¶ãÉÏ¯¼¾ÂéÇ¿¼ÖÂ·Â¦Î£½­Í¯ÑÕ¹ùÃ·Ê¢ÁÖµóÖÓĞìÇñÂæ¸ßÏÄ²ÌÌï·®"
				+ "ºúÁè»ôÓİÍòÖ§¿Â¾Ì¹ÜÂ¬Äª¾­·¿ôÃçÑ¸É½âÓ¦×ÚĞû¶¡êÚµËÓôµ¥º¼ºé°üÖî×óÊ¯´Ş¼ªÅ¥¹¨³ÌïúĞÏ»¬ÅáÂ½ÈÙÎÌÜ÷Ñòì¶»İÕçÎº¼Ó·âÜÇôà´¢½ù¼³Úû"
				+ "ÃÓËÉ¾®¶Î¸»Î×ÎÚ½¹°Í¹­ÄÁÚóÉ½¹È³µºîåµÅîÈ«Û­°àÑöÇïÖÙÒÁ¹¬Äş³ğèï±©¸Êî×À÷ÈÖ×æÎä·ûÁõ½ªÕ²ÊøÁúÒ¶ĞÒË¾ÉØÛ¬Àè¼»±¡Ó¡ËŞ°×»³ÆÑÌ¨´Ó"
				+ "¶õË÷ÏÌ¼®Àµ×¿İşÍÀÃÉ³ØÇÇÒõÓôñãÄÜ²ÔË«ÎÅİ·µ³µÔÌ·¹±ÀÍåÌ¼§Éê·ö¶ÂÈ½Ô×ÛªÓºÈ´è³É£¹ğå§Å£ÊÙÍ¨±ßìèÑà¼½Û£ÆÖÉĞÅ©ÎÂ±ğ×¯êÌ²ñöÄÑÖ³ä"
				+ "Ä½Á¬ÈãÏ°»Â°¬ÓãÈİÏò¹ÅÒ×É÷¸êÁÎ¸ıÖÕôß¾Óºâ²½¶¼¹¢Âúºë¿ï¹úÎÄ¿Ü¹ãÂ»ãÚ¶«Å¹ì¯ÎÖÀûÎµÔ½ÙçÂ¡Ê¦¹®ØÇÄôêË¹´°½ÈÚÀäö¤ĞÁãÛÄÇ¼òÈÄ¿ÕÔø" 
				+ "ÎãÉ³Ø¿Ñø¾ÏĞë·á³²¹ØØáÏà²éºó½­ºìÓÎóÃÈ¨åÖ¸ÇÒæ»¸¹«Íò";
		int lastNameSize = lastName.length();
		String firstName = "Î°¸ÕÓÂÒã¿¡·åÇ¿¾üÆ½±£¶«ÎÄ»ÔÁ¦Ã÷ÓÀ½¡ÊÀ¹ãÖ¾ÒåĞËÁ¼º£É½ÈÊ²¨Äş¹ó¸£ÉúÁúÔªÈ«¹úÊ¤Ñ§Ïé²Å·¢ÎäĞÂ"
				+ "ÀûÇå·É±ò¸»Ë³ĞÅ×Ó½ÜÌÎ²ı³É¿µĞÇ¹âÌì´ï°²ÑÒÖĞÃ¯½øÁÖÓĞ¼áºÍ±ë²©³ÏÏÈ¾´ÕğÕñ×³»áË¼ÈººÀĞÄ°î³ĞÀÖÉÜ¹¦ËÉÉÆºñÇìÀÚÃñÓÑÔ£ºÓÕÜ½­³¬ºÆ"
				+ "ÁÁÕşÇ«ºàÆæ¹ÌÖ®ÂÖº²ÀÊ²®ºêÑÔÈôÃùÅó±óÁº¶°Î¬Æô¿ËÂ×ÏèĞñÅôÔó³¿³½Ê¿ÒÔ½¨¼ÒÖÂÊ÷Ñ×µÂĞĞÊ±Ì©Ê¢ĞÛè¡¾û¹Ú²ßÌÚéªéÅ·çº½ºëĞã¾êÓ¢»ª»Û"
				+ "ÇÉÃÀÄÈ¾²Êç»İÖé´äÑÅÖ¥ÓñÆ¼ºì¶ğÁá·Ò·¼Ñà²Ê´º¾ÕÀ¼·ï½àÃ·ÁÕËØÔÆÁ«Õæ»·Ñ©ÈÙ°®ÃÃÏ¼ÏãÔÂİºæÂÑŞÈğ·²¼Ñ¼ÎÇíÇÚÕäÕêÀò¹ğæ·Ò¶èµè´æ«çù"
				+ "¾§åûÜçÇïÉºÉ¯½õ÷ìÇàÙ»æÃæ¯ÍñæµèªÓ±Â¶Ñşâùæ¿ÑãİíæıÒÇºÉµ¤ÈØÃ¼¾ıÇÙÈïŞ±İ¼ÃÎá°Ô·æ¼Ü°è¥çüÔÏÈÚÔ°ÒÕÓ½Çä´ÏÀ½´¿Ø¹ÔÃÕÑ±ùË¬çşÜøÓğ" 
				+ "Ï£ÄşĞÀÆ®ÓıäŞğ¥óŞÈáÖñö°ÄıÏş»¶Ïö·ãÜ¿·Æº®ÒÁÑÇÒË¿É¼§ÊæÓ°ÀóÖ¦Ë¼Àö";
		int firstNameSize = firstName.length();
		// Ãû×ÖÁ½¸ö×Ö»òÕßÈı¸ö×Ö
		int firstNameLenth = getRandomNum(2) + 1; 
		int index = getRandomNum(lastNameSize);
		StringBuilder name = new StringBuilder(lastName.substring(index, index + 1));
		for (int i = 1; i <= firstNameLenth; i++) {
			index = getRandomNum(firstNameSize);
			name.append(firstName.substring(index, index + 1));
		}
		return name.toString();
	}
    
    /**
     * »ñÈ¡Ëæ»úÉú³ÉµÄÉí·İÖ¤
     * @return String
     *
     * @author       luandao
     * @Date         2021Äê12ÔÂ23ÈÕ 
     *
     */
	public static String idNoGenerator() {
		StringBuilder idNo = new StringBuilder(18);
		idNo.append(provinces[getRandomNum(provinces.length)]);
		for (int i = 3; i <= 6; i++) {
			idNo.append(getRandomNum(10));
		}
		int year = 1950 + getRandomNum(50);
		idNo.append(year);
		int iMonth = getRandomNum(12) + 1;
		String sMonth = iMonth < 10 ? "0" + iMonth : String.valueOf(iMonth);
		int iDay = getRandomNum(30) + 1;
		String sDay = iDay < 10 ? "0" + iDay : String.valueOf(iDay);
		idNo.append(sMonth).append(sDay);
		for (int i = 15; i <= 18; i++) {
			idNo.append(getRandomNum(10));
		}
		return idNo.toString();
	}
   
	/**
	 * »ñÈ¡Ëæ»úÉú³ÉµÄÒøĞĞÃû³Æ
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2021Äê12ÔÂ23ÈÕ 
	 *
	 */
	public static String bankGenerator() {
		int size = bank.length;
		return bank[getRandomNum(size)];
	}
    
	/**
	 * »ñÈ¡Ëæ»úÉú³ÉµÄÒøĞĞ¿¨ºÅ
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2021Äê12ÔÂ23ÈÕ 
	 *
	 */
	public static String bandNoGenerator() {
		int bankHead = 621720 + getRandomNum(10000);
		StringBuilder bankNo = new StringBuilder(String.valueOf(bankHead));
		for (int i = 0; i < 10; i++) {
			bankNo.append(getRandomNum(10));
		}
		return bankNo.toString();
	}
}
