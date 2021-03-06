package org.shapelogic.imageprocessing;

import org.shapelogic.color.ColorUtil;
import org.shapelogic.color.IColorAndVariance;
import org.shapelogic.color.ValueAreaFactory;
import org.shapelogic.imageutil.SLImage;
import org.shapelogic.logic.CommonLogicExpressions;
import org.shapelogic.polygon.BBox;
import org.shapelogic.streamlogic.StreamNames;
import org.shapelogic.streams.NumberedStream;
import org.shapelogic.streams.StreamFactory;

import static org.shapelogic.imageutil.ImageUtil.runPluginFilterOnBufferedImage;

/** Test RGBColorParticleCounter. <br />
 * 
 * The difference of this from BaseParticleCounterTest is that this should test
 * more customized ParticleCounters, while BaseParticleCounterTest should test
 * the basic cases.
 * 
 * @author Sami Badawi
 *
 */
public class RGBColorParticleAnalyzerTest extends AbstractImageProcessingTests {
	ColorParticleAnalyzer _particleCounter;
	String _dir = "src/test/resources/data/neuralnetwork/";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		_dirURL = "./src/test/resources/images/particles";
		_fileFormat = ".gif";
        _particleCounter = new RGBColorParticleAnalyzerIJ();
        _particleCounter.setDisplayTable(false);
		_particleCounter.setUseReferenceAsBackground(true);
	}
	
	public void testWhitePixelGray() {
		String fileName = "oneWhitePixelGray";
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName), _particleCounter);
        _particleCounter.setInputColor(0);
		assertEquals(1,bp.getWidth());
		assertTrue(bp.isInvertedLut());
		int pixel = bp.get(0,0);
		assertEquals(0,pixel); //So this is a white background pixel
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(1,factory.getStore().size());
		assertTrue(_particleCounter.isParticleImage());
		assertEquals(0,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = factory.getStore().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(0.,bBox.minVal.getX());
		assertEquals(0.,bBox.minVal.getY());
		assertEquals(0.,bBox.maxVal.getX());
		assertEquals(0.,bBox.maxVal.getY());
	}

	/** This shows that when you save and image as PNG it will always be opened 
	 * as a color image.
	 */
	public void testOneWhitePixelGrayPng() {
		String fileName = "oneWhitePixelGray";
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertTrue(bp.isEmpty());
	}

	public void testBlobsGif() {
		String fileName = "blobs";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(7);
        _particleCounter.setInputColor(49);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(65024,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(40,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(64,factory.getStore().size()); 
//		assertTrue(_particleCounter.isParticleImage()); 
//		assertEquals(30,_particleCounter.getParticleCount()); 
//		assertTrue(bp.isInvertedLut());
	}

	public void testEmbryos() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(50);
        _particleCounter.setMinPixelsInArea(5);
        int rgbValue = ColorUtil.packColors(186, 165, 88);
        _particleCounter.setInputColor(rgbValue);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(30,factory.getStore().size());
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(6,_particleCounter.getParticleCount()); 
		StreamFactory streamFactory = new StreamFactory(_particleCounter);
		NumberedStream<Number> ns = streamFactory.findNumberedStream(CommonLogicExpressions.ASPECT_RATIO);
//		assertClose(1.17, ns.get(0).doubleValue(), 0.1); // aspect ratio stream is not defined
//		assertClose(1., ns.get(1).doubleValue(), 0.1);
		NumberedStream<String> letterStream = streamFactory.findNumberedStream(StreamNames.CATEGORY);
		assertEquals("Dark round", letterStream.get(0));
		assertEquals("Dark round", letterStream.get(1));
	}
	
	public void testEmbryosNeuralNetwor() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(50);
        _particleCounter.setMinPixelsInArea(5);
        _particleCounter.setUseNeuralNetwork(true);
        int rgbValue = ColorUtil.packColors(186, 165, 88);
        _particleCounter.setInputColor(rgbValue);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(30,factory.getStore().size());
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(6,_particleCounter.getParticleCount()); 
		StreamFactory streamFactory = new StreamFactory(_particleCounter);
		NumberedStream<String> letterStream = streamFactory.findNumberedStream(StreamNames.CATEGORY);
		assertEquals("Tall", letterStream.get(0));
		assertEquals("", letterStream.get(1));
	}
	
	public void testEmbryosNeuralNetworFile() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(50);
        _particleCounter.setMinPixelsInArea(5);
        String neuralNetworkFile = "particle_nn_with_print.txt";
        _particleCounter.setNeuralNetworkFile(_dir+neuralNetworkFile);
        _particleCounter.setUseNeuralNetwork(true);
        int rgbValue = ColorUtil.packColors(186, 165, 88);
        _particleCounter.setInputColor(rgbValue);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(30,factory.getStore().size());
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(6,_particleCounter.getParticleCount()); 
		StreamFactory streamFactory = new StreamFactory(_particleCounter);
		NumberedStream<String> letterStream = streamFactory.findNumberedStream(StreamNames.CATEGORY);
		assertEquals("tall", letterStream.get(0));
		assertEquals("", letterStream.get(1));
	}
	
	public void testEmbryosExternalRulesFile() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(50);
        _particleCounter.setMinPixelsInArea(5);
        String neuralNetworkFile = "particle_nn_with_rules.txt";
        _particleCounter.setNeuralNetworkFile(_dir+neuralNetworkFile);
        _particleCounter.setUseNeuralNetwork(false);
        int rgbValue = ColorUtil.packColors(186, 165, 88);
        _particleCounter.setInputColor(rgbValue);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(30,factory.getStore().size());
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(6,_particleCounter.getParticleCount()); 
		StreamFactory streamFactory = new StreamFactory(_particleCounter);
		NumberedStream<String> letterStream = streamFactory.findNumberedStream(StreamNames.CATEGORY);
		assertEquals("TALL", letterStream.get(0));
		assertEquals("", letterStream.get(1));
	}
	
	public void testEmbryosToMask() {
		String fileName = "embryos6";
        _particleCounter.setToMask(true);
        int rgbValue = ColorUtil.packColors(186, 165, 88);
        _particleCounter.setInputColor(rgbValue);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(0xffffff,pixel); //Background
		assertEquals(0,bp.get(128, 128)); //Foreground
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(30,factory.getStore().size()); //XXX should be 2
		assertTrue(_particleCounter.isParticleImage());
		assertEquals("Should have 6 particles for this setting.", 6,_particleCounter.getParticleCount());
	}

	public void assertClose(double expected, double found, double precision) {
		assertTrue(Math.abs(expected-found) < precision);
	}
	
	/** Debug version. */
	public void assertClose2(double expected, double found, double precision) {
		assertEquals(expected,found);
	}

	public void testEmbryosWithParameters() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(20);
        _particleCounter.setIterations(3);
        String parameters = "iterations=4 maxDistance=90 minPixelsInArea=70";
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter,parameters);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
//		assertEquals(383,factory.getStore().size()); //XXX should be 2
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(5,_particleCounter.getParticleCount()); 
	}

	public void testEmbryosMoreIterations() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(20);
        _particleCounter.setIterations(4);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(66,factory.getStore().size()); 
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(7,_particleCounter.getParticleCount()); 
	}

	/** This gets opened as a byte interleaved and not as an int RGB
	 */
	public void testCleanSpotPng() {
		String fileName = "spot1Clean";
		final int background = 0xffffff;
		final int foreground = 0;
		_particleCounter.setUseReferenceAsBackground(true);
		_particleCounter.setInputColor(background);
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(900,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(background,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(8.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(22.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(15.,bBox.getCenter().getY());
		assertEquals(1.,bBox.getAspectRatio());
	}

	public void testCleanSpotPngForground() {
		String fileName = "spot1Clean";
		final int background = 0xffffff;
		final int foreground = 0;
		_particleCounter.setUseReferenceAsBackground(false);
		_particleCounter.setInputColor(foreground);
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(900,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(background,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(8.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(22.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(15.,bBox.getCenter().getY());
		assertEquals(1.,bBox.getAspectRatio());
	}

	/** Test with default categorizer. <br />
	 * This gets opened as a byte interleaved and not as an int RGB.
	 */
	public void testOvalCleanPng() {
		String fileName = "oval1Clean";
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(1800,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(0xffffff,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(16.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(45.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(30.,bBox.getCenter().getY());
		assertEquals(0.4827586206896552,bBox.getAspectRatio());
	}

	/**  Test with external rules categorizer. <br />
	 * This gets opened as a byte interleaved and not as an int RGB.
	 */
	public void testOvalCleanPngExternalRules() {
		String fileName = "oval1Clean";
        String neuralNetworkFile = "particle_nn_with_rules.txt";
        _particleCounter.setNeuralNetworkFile(_dir+neuralNetworkFile);
        _particleCounter.setUseNeuralNetwork(false);
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(1800,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(0xffffff,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(16.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(45.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(30.,bBox.getCenter().getY());
		assertEquals(0.4827586206896552,bBox.getAspectRatio());
		StreamFactory streamFactory = new StreamFactory(_particleCounter);
		NumberedStream<String> letterStream = streamFactory.findNumberedStream(StreamNames.CATEGORY);
		assertEquals("TALL", letterStream.get(0));
	}

	/**  Test with neural network categorizer. <br />
	 * This gets opened as a byte interleaved and not as an int RGB.
	 */
	public void testOvalCleanPngExternalNN() {
		String fileName = "oval1Clean";
        String neuralNetworkFile = "particle_nn_with_print.txt";
        _particleCounter.setNeuralNetworkFile(_dir+neuralNetworkFile);
        _particleCounter.setUseNeuralNetwork(true);
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(1800,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(0xffffff,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(16.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(45.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(30.,bBox.getCenter().getY());
		assertEquals(0.4827586206896552,bBox.getAspectRatio());
		StreamFactory streamFactory = new StreamFactory(_particleCounter);
		NumberedStream<String> letterStream = streamFactory.findNumberedStream(StreamNames.CATEGORY);
		assertEquals("tall", letterStream.get(0));
	}
}
