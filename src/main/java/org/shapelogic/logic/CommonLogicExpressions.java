package org.shapelogic.logic;

/** Contains commonly used logic expression strings
 * So all the logic expressions that are used in the letter match is defined here.
 * There are both a name and a logic expression defined for each.
 * 
 * @author Sami Badawi
 *
 */
public class CommonLogicExpressions {
	
	
	// Logic related
	public static final String AND = " && ";
	
	// Filter related
	public static final String FILTER_START = "#.filter('";
	public static final String FILTER_END = "').size()";
	
	// Filter related
	public static final String VAR = "#.";
	public static final String VAR_SIZE_START = "size(#.";
	public static final String VAR_SIZE_END = ")";
	
	//Point related
	public static final String POINT_COUNT = "pointCount";
	public static final String POINT_COUNT_EX = "getPoints()";

	public static final String END_POINT_COUNT = "endPointCount";
	public static final String END_POINT_COUNT_EX = "getEndPointCount()";
	//Other way of counting end points using annotations
	public static final String END_COUNT_ANNOTATION_EX = "PointOfTypeFilter(PointType.END_POINT)";

	//Point location related
	public static final String LEFT_HALF_EX = "PointLeftOfFilter(0.5)";
	public static final String LEFT_THIRD_EX = "PointLeftOfFilter(0.3333)";
	public static final String RIGHT_HALF_EX = "PointRightOfFilter(0.5)";
	public static final String RIGHT_THIRD_EX = "PointRightOfFilter(0.6666)";
	public static final String CENTER_THIRD_EX = 
		"PointRightOfFilter(0.3333) && PointLeftOfFilter(0.6666)";

	public static final String TOP_HALF_EX = "PointAboveFilter(0.5)";	
	public static final String TOP_THIRD_EX = "PointAboveFilter(0.3333)";
	public static final String BOTTOM_THIRD_EX = "PointBelowFilter(0.6666)";	
	public static final String BOTTOM_HALF_EX = "PointBelowFilter(0.5)";	
	public static final String MIDDLE_THIRD_EX = 
		"PointBelowFilter(0.3333) && PointAboveFilter(0.6666)";
	
	public static final String TOP_LEFT_HALF_EX = TOP_HALF_EX + AND + LEFT_HALF_EX;	
	public static final String TOP_RIGHT_HALF_EX = TOP_HALF_EX + AND + RIGHT_HALF_EX;	
	public static final String BOTTOM_LEFT_HALF_EX = BOTTOM_HALF_EX + AND + LEFT_HALF_EX;	
	public static final String BOTTOM_RIGHT_HALF_EX = BOTTOM_HALF_EX + AND + RIGHT_HALF_EX;	
	
	public static final String TOP_LEFT_THIRD_EX = TOP_THIRD_EX + AND + LEFT_THIRD_EX;
	public static final String TOP_CENTER_THIRD_EX = TOP_THIRD_EX + AND + CENTER_THIRD_EX;
	public static final String TOP_RIGHT_THIRD_EX = TOP_THIRD_EX + AND + RIGHT_THIRD_EX;
	public static final String MIDDLE_LEFT_THIRD_EX = MIDDLE_THIRD_EX + AND + LEFT_THIRD_EX;
	public static final String MIDDLE_CENTER_THIRD_EX = MIDDLE_THIRD_EX + AND + CENTER_THIRD_EX;
	public static final String MIDDLE_RIGHT_THIRD_EX = MIDDLE_THIRD_EX + AND + RIGHT_THIRD_EX;
	public static final String BOTTOM_LEFT_THIRD_EX = BOTTOM_THIRD_EX + AND + LEFT_THIRD_EX;	
	public static final String BOTTOM_CENTER_THIRD_EX = BOTTOM_THIRD_EX + AND + CENTER_THIRD_EX;
	public static final String BOTTOM_RIGHT_THIRD_EX = BOTTOM_THIRD_EX + AND + RIGHT_THIRD_EX;	
	
	//Point annotation related
	public static final String HARD_CORNER_COUNT = "hardPointCount";
	public static final String HARD_CORNER_COUNT_EX = "PointOfTypeFilter(\"HARD_CORNER\")";
	public static final String HARD_CORNER_COUNT_ANN_EX = "getAnnotatedShape().getShapesForAnnotation(\"PointType.HARD_CORNER\")";
	
	public static final String SOFT_POINT_COUNT = "softPointCount";
	public static final String SOFT_POINT_COUNT_EX = "PointOfTypeFilter(\"SOFT_POINT\")";
	public static final String SOFT_POINT_COUNT_ANN_EX = "getAnnotatedShape().getShapesForAnnotation(\"PointType.SOFT_POINT\")";

	public static final String Y_JUNCTION_POINT_COUNT = "yJunctionCount";
	public static final String Y_JUNCTION_POINT_COUNT_EX = "PointOfTypeFilter(\"Y_JUNCTION\")";

	public static final String T_JUNCTION_POINT_COUNT = "tJunctionCount";
	public static final String T_JUNCTION_POINT_COUNT_EX = "PointOfTypeFilter(\"T_JUNCTION\")";
	
	public static final String U_JUNCTION_POINT_COUNT = "uJunctionCount";
	public static final String U_JUNCTION_POINT_COUNT_EX = "PointOfTypeFilter(\"U_JUNCTION\")";
	public static final String U_JUNCTION_POINT_COUNT_ANN_EX = "getAnnotatedShape().getShapesForAnnotation(\"PointType.U_JUNCTION\")";

	public static final String INFLECTION_POINT_COUNT = "inflectionPointCount";
	public static final String INFLECTION_POINT_COUNT_EX = "getAnnotatedShape().getShapesForAnnotation(\"LineType.INFLECTION_POINT\")";

	public static final String CURVE_ARCH_COUNT = "curveArchCount";
	public static final String CURVE_ARCH_COUNT_ANN_EX = "getAnnotatedShape().getShapesForAnnotation(\"LineType.CURVE_ARCH\")";
	
	
	//Point location and annotation related
	public static final String T_JUNCTION_LEFT_POINT_COUNT = "tJunctionLeftCount";
	public static final String T_JUNCTION_LEFT_POINT_COUNT_EX =
		T_JUNCTION_POINT_COUNT_EX + AND + LEFT_HALF_EX; 
	
	public static final String T_JUNCTION_RIGHT_POINT_COUNT = "tJunctionRightCount";
	public static final String T_JUNCTION_RIGHT_POINT_COUNT_EX = 
		T_JUNCTION_POINT_COUNT_EX + AND + RIGHT_HALF_EX; 

	public static final String U_JUNCTION_RIGHT_POINT_COUNT = "tJunctionRightCount";
	public static final String U_JUNCTION_RIGHT_POINT_COUNT_EX = 
		U_JUNCTION_POINT_COUNT_EX + AND + RIGHT_HALF_EX; 

	public static final String END_POINT_BOTTOM_POINT_COUNT = "endpointBottomPointCount"; 
	public static final String END_POINT_BOTTOM_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + BOTTOM_HALF_EX;

	public static final String END_POINT_TOP_POINT_COUNT =  "endpointTopPointCount";
	public static final String END_POINT_TOP_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + TOP_HALF_EX;
	
	public static final String END_POINT_TOP_LEFT_THIRD_POINT_COUNT =  "endpointTopLeftThirdPointCount";
	public static final String END_POINT_TOP_LEFT_THIRD_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + TOP_LEFT_THIRD_EX;
	
	public static final String END_POINT_TOP_CENTER_THIRD_POINT_COUNT =  "endpointTopCenterThirdPointCount";
	public static final String END_POINT_TOP_CENTER_THIRD_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + TOP_CENTER_THIRD_EX;
	
	public static final String END_POINT_TOP_RIGHT_THIRD_POINT_COUNT =  "endpointTopRightThirdPointCount";
	public static final String END_POINT_TOP_RIGHT_THIRD_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + TOP_RIGHT_THIRD_EX;
	
	public static final String END_POINT_TOP_RIGHT_HALF_POINT_COUNT =  "endpointTopRightHalfPointCount";
	public static final String END_POINT_TOP_RIGHT_HALF_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + TOP_RIGHT_HALF_EX;
	
	public static final String END_POINT_BOTTOM_RIGHT_HALF_POINT_COUNT =  "endpointBottomRightHalfPointCount";
	public static final String END_POINT_BOTTOM_RIGHT_HALF_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + BOTTOM_RIGHT_HALF_EX;
	
	public static final String END_POINT_BOTTOM_LEFT_HALF_POINT_COUNT =  "endpointBottomLeftHalfPointCount";
	public static final String END_POINT_BOTTOM_LEFT_HALF_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + BOTTOM_LEFT_HALF_EX;
	
	public static final String END_POINT_BOTTOM_LEFT_THIRD_POINT_COUNT =  "endpointBottomLeftThirdPointCount";
	public static final String END_POINT_BOTTOM_LEFT_THIRD_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + BOTTOM_LEFT_THIRD_EX;
	
	public static final String END_POINT_BOTTOM_CENTER_THIRD_POINT_COUNT =  "endpointBottomCenterThirdPointCount";
	public static final String END_POINT_BOTTOM_CENTER_THIRD_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + BOTTOM_CENTER_THIRD_EX;
		
	public static final String END_POINT_BOTTOM_RIGHT_THIRD_POINT_COUNT =  "endpointBottomRightThirdPointCount";
	public static final String END_POINT_BOTTOM_RIGHT_THIRD_POINT_COUNT_EX = 
		END_COUNT_ANNOTATION_EX + AND + BOTTOM_RIGHT_THIRD_EX;
	
	//Line related
	public static final String LINE_COUNT = "lineCount";
	public static final String LINE_COUNT_EX = "getLines()";

	public static final String HORIZONTAL_LINE_COUNT = "horizontalLineCount";
	public static final String HORIZONTAL_LINE_COUNT_EX = "getHorizontalLines()";

	public static final String VERTICAL_LINE_COUNT = "verticalLineCount";
	public static final String VERTICAL_LINE_COUNT_EX = "getVerticalLines()";
	
	public static final String STRAIGHT_LINE_COUNT = "straightLineCount";
	public static final String STRAIGHT_LINE_COUNT_ANN_EX = "getAnnotatedShape().getShapesForAnnotation(\"LineType.STRAIGHT\")";

	//Multi line related
	public static final String MULTI_LINE_COUNT = "multiLineCount";
	public static final String MULTI_LINE_COUNT_EX = "getMultiLines()";
	
	//Polygon related
	public static final String HOLE_COUNT = "holeCount";
	public static final String HOLE_COUNT_EX = "holeCount";
	
	public static final String ASPECT_RATIO = "aspectRatio";
	public static final String ASPECT_RATIO_EX = "getBBox().getAspectRatio()";
	
}
