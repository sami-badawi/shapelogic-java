package org.shapelogic.imageprocessing;

import java.util.Set;

import org.shapelogic.imageutil.SLImage;
import org.shapelogic.polygon.AnnotatedShape;
import org.shapelogic.polygon.GeometricShape2D;
import org.shapelogic.polygon.Polygon;
import org.shapelogic.util.LineType;
import org.shapelogic.util.PointType;

import static org.shapelogic.imageutil.ImageUtil.runPluginFilterOnImage;

/** LetterMatchingStreamVectorizerTest unit test for letter matching for StreamVectorizer.
 * <br />  
 * The main letter matching and vectorization algorithm, 
 * that works for both straight and curved capital letters
 * Note that this is sub classed from another unit test for more basic tests 
 * that only work for straight letters  
 * 
 * @author Sami Badawi
 *
 */
public class LetterMatchingStreamVectorizerTest extends BaseLetterMatchingMaxDistanceVectorizerTests {

    StreamVectorizer _streamVectorizer;
    String _dataDir = "src/test/resources/data/neuralnetwork";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
        vectorizer = _streamVectorizer = new StreamVectorizer();
	}
	
	public void testABC() {
		String fileName = "ABC";
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		int pixel = bp.get(0,0);
		assertEquals(PixelType.BACKGROUND_POINT.color,pixel);
		Polygon polygon = vectorizer.getPolygon();
		Polygon improvedPolygon = polygon.improve(); 
		AnnotatedShape annotations = improvedPolygon.getAnnotatedShape();
		Set<GeometricShape2D> endPoints = annotations.getShapesForAnnotation(PointType.END_POINT);
		System.out.println("End points: " + endPoints);
		Set<GeometricShape2D> inflectionPoints = annotations.getShapesForAnnotation(LineType.INFLECTION_POINT);
		assertEmptyCollection(inflectionPoints);
		printAnnotaions(polygon);
		Polygon cleanedPolygon = vectorizer.getCleanedupPolygon();
		assertEquals("A; B; C",vectorizer.getMatchingOH());
	}

	public void testABC_LettersForHolesWithNeuralNetwork() {
		String fileName = "ABC";
        String dataFileName = "polygon_nn_with_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(true);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		int pixel = bp.get(0,0);
		assertEquals(PixelType.BACKGROUND_POINT.color,pixel);
		Polygon polygon = vectorizer.getPolygon();
		Polygon improvedPolygon = polygon.improve();
		AnnotatedShape annotations = improvedPolygon.getAnnotatedShape();
		Set<GeometricShape2D> endPoints = annotations.getShapesForAnnotation(PointType.END_POINT);
		System.out.println("End points: " + endPoints);
//		assertEquals(2, endPoints.size());
		Set<GeometricShape2D> inflectionPoints = annotations.getShapesForAnnotation(LineType.INFLECTION_POINT);
		assertEmptyCollection(inflectionPoints);
		printAnnotaions(polygon);
		Polygon cleanedPolygon = vectorizer.getCleanedupPolygon();
		assertEquals("Holes; Holes; No_holes",vectorizer.getMatchingOH());
	}

	public void testABC_LettersForHolesWithExternalRules() {
		String fileName = "ABC";
        String dataFileName = "polygon_nn_with_rules_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(false);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		int pixel = bp.get(0,0);
		assertEquals(PixelType.BACKGROUND_POINT.color,pixel);
		Polygon polygon = vectorizer.getPolygon();
		Polygon improvedPolygon = polygon.improve();
		AnnotatedShape annotations = improvedPolygon.getAnnotatedShape();
		Set<GeometricShape2D> endPoints = annotations.getShapesForAnnotation(PointType.END_POINT);
		System.out.println("End points: " + endPoints);
//		assertEquals(2, endPoints.size());
		Set<GeometricShape2D> inflectionPoints = annotations.getShapesForAnnotation(LineType.INFLECTION_POINT);
		assertEmptyCollection(inflectionPoints);
		printAnnotaions(polygon);
		Polygon cleanedPolygon = vectorizer.getCleanedupPolygon();
		assertEquals("Holes; Holes; NoHoles",vectorizer.getMatchingOH());
	}

	public void test0() //The digit 0
    {
		String fileName = "O"; //This is actually the letter
        String dataFileName = "polygon_digit_recognizer_with_rules_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(false);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals("0",vectorizer.getMatchingOH());
	}

	public void test1() //The digit 1
    {
		String fileName = "I"; //This is actually the letter
        String dataFileName = "polygon_digit_recognizer_with_rules_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(false);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals("1",vectorizer.getMatchingOH());
	}

    public void simpleDigit(String fileName)
    {
        String dataFileName = "polygon_digit_recognizer_with_rules_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(false);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals(fileName, vectorizer.getMatchingOH());
	}

	public void test2() {
        simpleDigit("2");
    }

	public void test3() {
        simpleDigit("3");
    }

	public void test4() {
        simpleDigit("4");
    }

	public void test5() {
        simpleDigit("5");
    }

	public void test6() {
        simpleDigit("6");
    }

	public void test7() {
        simpleDigit("7");
    }

	public void test8() {
        simpleDigit("8");
    }

	public void test9() {
        simpleDigit("9");
    }

    public void testAWithMissingNeuralNetwork()
    {
        String fileName = "A";
        String dataFileName = "polygon_digit_recognizer_with_rules_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(true);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals("Holes", vectorizer.getMatchingOH());
	}

    public void testAWithNeuralNetwork()
    {
        String fileName = "A";
        String dataFileName = "polygon_nn_with_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(true);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals("Holes", vectorizer.getMatchingOH());
	}

    public void testAWithMissingRules()
    {
        String fileName = "A";
        String dataFileName = "polygon_nn_with_print.txt";
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(false);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals(fileName, vectorizer.getMatchingOH());
	}

    /** This match is done with a bad categorizer setup file meant for particles.<br /> 
     * It should not throw an exception.
     */
    public void testAWithMissingStreamNN()
    {
        String fileName = "A";
        String dataFileName = "particle_nn_with_print.txt"; 
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(true);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		String errorMessage = "NumberedStream not found in context for name: \"aspect\".\n Nothing found.";
		assertEquals(errorMessage, vectorizer.getErrorMessage());
		assertEquals(null, vectorizer.getMatchingOH());
	}

    /** This match is done with a bad categorizer setup file meant for particles.<br /> 
     * It should not throw an exception.
     */
    public void testAWithMissingStreamRules()
    {
        String fileName = "A";
        String dataFileName = "particle_nn_with_print.txt"; 
        _streamVectorizer.setNeuralNetworkFile(_dataDir + "/" + dataFileName);
        _streamVectorizer.setUseNeuralNetwork(false);
		SLImage bp =runPluginFilterOnImage(filePath(fileName), vectorizer);
		assertEquals(null, vectorizer.getErrorMessage());
		assertEquals(fileName, vectorizer.getMatchingOH());
	}

}
