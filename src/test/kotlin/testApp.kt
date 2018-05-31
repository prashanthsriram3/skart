import junit.framework.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class TestApp {
    @Test
    fun testEmptyCart() {
        val shoppingCart = ShoppingCart()
        shoppingCart.calculateTotal()
        assertEquals("Test failed", BigDecimal("0.00"), shoppingCart.total)
    }

    @Test
    fun testAddFiveProducts() {
        val doveSoaps = (0..4).map { Product("Dove Soap", BigDecimal("39.99")) }
        val shoppingCart = ShoppingCart(doveSoaps.toMutableList())
        shoppingCart.calculateTotal()
        assertEquals("Test failed", BigDecimal("199.95"), shoppingCart.total)
    }

    @Test
    fun testAddFiveAndThenThreeProducts() {
        val doveSoap = Product("Dove Soap", BigDecimal("39.99"))
        val shoppingCart = ShoppingCart()
        (0..4).forEach { shoppingCart.addItem(doveSoap) }
        (0..2).forEach { shoppingCart.addItem(doveSoap) }
        shoppingCart.calculateTotal()
        assertEquals("Test failed", BigDecimal("319.92"), shoppingCart.total)
    }

    @Test
    fun testCalculateTax() {
        val shoppingCart = ShoppingCart(taxRate = BigDecimal("12.5"))
        val doveSoap = Product("Dove Soap", BigDecimal("39.99"))
        val axeDeo = Product("Axe Deo", BigDecimal("99.99"))
        (0..1).forEach { shoppingCart.addItem(doveSoap) }
        (0..1).forEach { shoppingCart.addItem(axeDeo) }
        shoppingCart.calculateTotal()
        assertEquals("Tax total error", BigDecimal("35.00"), shoppingCart.totalTax)
        assertEquals("Test failed", BigDecimal("314.96"), shoppingCart.total)
    }
}