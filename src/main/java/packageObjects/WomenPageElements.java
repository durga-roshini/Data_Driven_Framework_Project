package packageObjects;

public interface WomenPageElements {
    String lnkWomen = "(//a[contains(text(),'Women')])[1]";
    String priceRange = "//dt[contains(text(),'Price')]/following::a[2]";
    String selectedPrice = "(//span[contains(text(),'Price:')]/following::span[1])[1]";
    String priceList = "//div[@class = 'category-products']//span[@class = 'price']";
    String drpdownSortByWomen = "(//select[@title = 'Sort By'])[1]";
    String listOFProductsByName = "//div[@class = 'category-products']//h2[@class = 'product-name']";
    String listOfProductsByPrice = "//div[@class = 'category-products']//span[@class = 'regular-price']";
    String sortSwitch = "//a[@class='sort-by-switcher sort-by-switcher--asc']";
    String verifyCompareSuccessMsg = "//li[@class='success-msg']//span";
    String compareBtn = "(//button[@class='button'])[1]";
    String lnkClearAll = "//a[contains(text(),'Clear All')]";
}
