package org.shapelogic.imageprocessing;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.io.OpenDialog;
import ij.measure.ResultsTable;
import ij.plugin.filter.ExtendedPlugInFilter;
import ij.plugin.filter.PlugInFilterRunner;
import ij.process.ImageProcessor;

import org.shapelogic.imageutil.IJGui;
import org.shapelogic.imageutil.IJImage;
import org.shapelogic.imageutil.SLImage;
import org.shapelogic.reporting.IJTableBuilder;
import org.shapelogic.util.Headings;

/** Line vectorizer and categorizer as an ImageJ ExtendedPlugInFilter.<br />
 * <br />
 * 
 * Works with rectangular ROIs<br />
 * 
 * @author Sami Badawi
 *
 */
public class StreamVectorizerIJ extends StreamVectorizer implements ExtendedPlugInFilter {

	protected ResultsTable _rt;
    protected GenericDialog _gd;
    
    protected static boolean _noMatchStatic = false;
    protected static boolean _toMaskStatic = false;
    protected static boolean _displayInternalInfoStatic = false;
    protected static boolean _displayResultTableStatic = true;
    protected static boolean _displayInputDialogStatic = true;
    protected static boolean _useNeuralNetworkStatic = false;
    protected static String _neuralNetworkFileStatic = null;

	@Override
	public void run(ImageProcessor ip) {
		run();
	}

	@Override
	public int setup(String arg, ImagePlus imp) {
		_guiWrapper = IJGui.INSTANCE;
        if (arg != null && arg.indexOf("InternalInfo") != -1) {
            _displayAll = true;
        }
		if (imp == null)
			return setup(arg, (SLImage)null);
		return setup(arg, new IJImage(imp));
	}
	
	@Override
	protected void displayResultsTable() {
    	_rt.show("Polygon properties");
	}

	@Override
	public void setNPasses(int passes) {
	}

	@Override
	public int showDialog(ImagePlus imp, String command, PlugInFilterRunner pfr) {
		if (_displayInputDialogStatic || _displayAll) {
	        _gd = new GenericDialog(getClass().getSimpleName(), IJ.getInstance());
	        _gd.addCheckbox("DisplayInputDialog: ", _displayInputDialogStatic);
	        _gd.addCheckbox("DisplayResultTable: ", _displayResultTableStatic);
	        _gd.addCheckbox("DisplayInternalInfo: ", _displayInternalInfoStatic);
	        _gd.addCheckbox("UseNeuralNetwork", _useNeuralNetworkStatic);
            _gd.addCheckbox("ShowFileDialog", false);
            _gd.addStringField("CategorizerSetupFile", _neuralNetworkFileStatic, 50); 
	        _gd.showDialog();
	        if (_gd.wasCanceled()) {
	            return DONE;
	        }
            _displayInputDialogStatic = _gd.getNextBoolean();
            _displayResultTable = _displayResultTableStatic = _gd.getNextBoolean();
	        _displayInternalInfo = _displayInternalInfoStatic = _gd.getNextBoolean();
	        _useNeuralNetwork = _useNeuralNetworkStatic = _gd.getNextBoolean();
            boolean showFileDialog = _gd.getNextBoolean();
            String tempFilePath = null;
            if (showFileDialog) {
                OpenDialog od = new OpenDialog("Choose a .txt config file", null);
                tempFilePath = ColorParticleAnalyzerIJ.getFilePath(od);
                if (tempFilePath != null && 0 < tempFilePath.trim().length())
                    _neuralNetworkFile = _neuralNetworkFileStatic = tempFilePath;
            }
            if (tempFilePath == null || tempFilePath.trim().length() == 0)
                _neuralNetworkFile = _neuralNetworkFileStatic = _gd.getNextString();
		}
        return IJ.setupDialog(imp, _setupReturnValue);
	}

	@Override
	protected void setupTableBuilder() {
	    _rt = new ResultsTable();
        _tableBuilder = new IJTableBuilder(_tableDefinition, _rt);
    }

	@Override
	protected boolean populateResultsTableRow(int index) {
		try {
            _tableBuilder.buildLine(index);
			return true;
		} catch (RuntimeException e) {
			String errorMessage = "Error: " + e.getMessage();
			_rt.addLabel(Headings.CATEGORY, errorMessage);
			System.out.println(errorMessage);
			e.printStackTrace();
			return false;
		}
	}

//	@Override
//    public void displayInternalInfo() {
//		StringBuffer result = getInternalInfo();
//        IJ.log(result.toString());
//    }
}

