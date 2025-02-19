public class InventoryClientSub {

    public static void stubInventoryCall(String skuCode, Integer quantity) {
        // stubbing the inventory call
        stubFor(get(urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200).withBody("true")));
    }
}
