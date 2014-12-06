package org.concordion.ext.demo;

import org.concordion.ext.driver.page.GoogleResultsPage;
import org.concordion.ext.driver.page.GoogleSearchPage;
import org.concordion.ext.driver.web.SeleniumScreenshotTaker;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.NotificationCard;
import org.concordion.ext.storyboard.StockCardImage;

/**
 * A fixture class for the ScreenshotDemo.html specification.
 * <p>
 * This adds the Screenshot Extension to Concordion to include screenshots on error in the Concordion output. By default this extension uses java.awt.Robot to
 * take the screenshot.
 * <p>
 * To include just the browser screen in the results, we configure the extension using the {@link ScreenshotExtensionFactory} and
 * {@link SeleniumScreenshotTaker} to take screenshots using WebDriver's TakesScreenshot interface.
 * <p>
 * Run this class as a JUnit test to produce the Concordion results. The test is expected to fail, since Google Calculator doesn't special case the answer to
 * life, the universe and everything.
 */
public class StoryboardDemoSuccess extends AcceptanceTest {

	private GoogleSearchPage searchPage;
	private GoogleResultsPage resultsPage;

	/**
	 * Searches for the specified topic, and waits for the results page to load.
	 */
	public void searchFor(final String topic) {
		getStoryboard().startCollapsibleGroup("Example");

		getStoryboard().addNotification("Soap", "Request Example", "<e1>\n\t<e2>hello</e2>\n</e1>", StockCardImage.XML_REQUEST, CardResult.SUCCESS);
		getStoryboard().addNotification("Soap", "Response Example", "<e1>\n\t<e2>hello</e2>\n</e1>", StockCardImage.XML_RESPONSE, CardResult.WARN);

		NotificationCard card = new NotificationCard();
		card.setTitle("Email");
		card.setDescription("Email Example");
		card.setData("<b>html formatted email</b>");
		card.setCardImage(StockCardImage.EMAIL);
		card.setFileExtension("html");

		getStoryboard().addCard(card);

		getStoryboard().stopCollapsibleGroup();

		searchPage = new GoogleSearchPage(getBrowser().getDriver(), getStoryboard());
		resultsPage = searchPage.searchFor(topic);
	}

	/**
	 * Returns the result from Google calculation.
	 */
	public String getCalculatorResult() {
		return resultsPage.getCalculatorResult();
	}
}