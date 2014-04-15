package com.yeep.basis.swing.widget.font;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.yeep.basis.swing.layout.XYConstraints;
import com.yeep.basis.swing.layout.XYLayout;
import com.yeep.basis.swing.widget.list.ListWidget;
import com.yeep.basis.swing.widget.list.ListWidgetModel;

@SuppressWarnings("serial")
public class FontWidget extends JPanel
{
	private static final String SAMPLE_STRING = "AaBbCcXxYyZz";
	private static final int UNKNOWN_FONT_STYLE = 0;
	private Font font;

	// widgets
	private JLabel fontLabel;
	private JLabel fontStyleLabel;
	private JLabel fontSizeLabel;
	private JTextField fontTextBox;
	private JTextField fontStyleTextBox;
	private JTextField fontSizeTextBox;
	private ListWidget<FontNameModel> fontListWidget;
	private ListWidget<FontStyleModel> fontStyleListWidget;
	private ListWidget<FontSizeModel> fontSizeListWidget;

	private JScrollPane fontScrollPanel;
	private JScrollPane fontStyleScrollPanel;
	private JScrollPane fontSizeScrollPanel;

	private JTextField sampleTextField;

	// Constructor
	public FontWidget(Font font)
	{
		super();
		initlayout();
		initData();
		setDefaultFont(font);
	}

	/**
	 * Initialize layout
	 */
	private void initlayout()
	{
		// Create widgets
		createWidgets();

		// init layout
		setLayout(new XYLayout());

		add(this.fontLabel, new XYConstraints(10, 10, 150, 20));
		add(this.fontStyleLabel, new XYConstraints(165, 10, 150, 20));
		add(this.fontSizeLabel, new XYConstraints(320, 10, 90, 20));

		add(this.fontTextBox, new XYConstraints(10, 32, 150, 25));
		add(this.fontStyleTextBox, new XYConstraints(165, 32, 150, 25));
		add(this.fontSizeTextBox, new XYConstraints(320, 32, 90, 25));

		add(this.fontScrollPanel, new XYConstraints(10, 59, 150, 100));
		add(this.fontStyleScrollPanel, new XYConstraints(165, 59, 150, 100));
		add(this.fontSizeScrollPanel, new XYConstraints(320, 59, 90, 100));

		add(this.sampleTextField, new XYConstraints(10, 165, 405, 80));
	}

	/**
	 * Create widgets
	 */
	private void createWidgets()
	{
		this.fontLabel = new JLabel("Font:");
		this.fontStyleLabel = new JLabel("Font style:");
		this.fontSizeLabel = new JLabel("Size:");

		this.fontTextBox = new JTextField();
		this.fontTextBox.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				FontNameModel fontNameModel = forFontName(fontTextBox.getText());
				if (fontNameModel != null)
				{
					font = new Font(fontNameModel.getValue(), font.getStyle(), font.getSize());
					renderFontName(font.getName());
					repaintSample();
				}
			}
		});

		this.fontStyleTextBox = new JTextField();
		this.fontStyleTextBox.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				int style = getFontStyle(fontStyleTextBox.getText());
				if (UNKNOWN_FONT_STYLE != style)
				{
					FontStyleModel fontStyleModel = forFontStyle(style);
					if (fontStyleModel != null)
					{
						font = new Font(font.getName(), fontStyleModel.getValue(), font.getSize());
						renderFontStyle(font.getStyle());
						repaintSample();
					}
				}
			}
		});

		this.fontSizeTextBox = new JTextField();
		this.fontSizeTextBox.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				try
				{
					long size = Math.round(Double.parseDouble(fontSizeTextBox.getText()));
					if (size >= 1 && size <= 48)
					{
						font = new Font(font.getName(), font.getStyle(), (int) size);
						renderFontSize(font.getSize());
						repaintSample();
					}
				}
				catch (Exception exception)
				{
					exception.printStackTrace();
				}
			}
		});

		this.fontListWidget = new ListWidget<FontNameModel>();
		this.fontListWidget.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				// If either of these are true, the event can be ignored.
				if ((!e.getValueIsAdjusting()) || (e.getFirstIndex() == -1))
					return;

				font = new Font(fontListWidget.getSelected().getValue(), font.getStyle(), font.getSize());
				renderFontName(font.getName());
				repaintSample();
			}
		});

		this.fontStyleListWidget = new ListWidget<FontStyleModel>();
		this.fontStyleListWidget.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				// If either of these are true, the event can be ignored.
				if ((!e.getValueIsAdjusting()) || (e.getFirstIndex() == -1))
					return;

				font = new Font(font.getName(), fontStyleListWidget.getSelected().getValue(), font.getSize());
				renderFontStyle(font.getStyle());
				repaintSample();
			}
		});

		this.fontSizeListWidget = new ListWidget<FontSizeModel>();
		this.fontSizeListWidget.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				// If either of these are true, the event can be ignored.
				if ((!e.getValueIsAdjusting()) || (e.getFirstIndex() == -1))
					return;

				font = new Font(font.getName(), font.getStyle(), fontSizeListWidget.getSelected().getValue());
				renderFontSize(font.getSize());
				repaintSample();
			}
		});

		this.fontScrollPanel = new JScrollPane(this.fontListWidget, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.fontStyleScrollPanel = new JScrollPane(this.fontStyleListWidget,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.fontSizeScrollPanel = new JScrollPane(this.fontSizeListWidget,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		this.sampleTextField = new JTextField();
		this.sampleTextField.setBorder(BorderFactory.createTitledBorder("Sample"));
		this.sampleTextField.setText(SAMPLE_STRING);
		this.sampleTextField.setHorizontalAlignment(JTextField.CENTER);
		this.sampleTextField.setColumns(20);
		this.sampleTextField.setPreferredSize(new Dimension(405, 80));
		this.sampleTextField.setEditable(false);

	}

	/**
	 * Initialize data for widget
	 */
	private void initData()
	{
		// init font name
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.CHINA);
		List<FontNameModel> fontNameList = new ArrayList<FontNameModel>();
		for (String fontName : fonts)
		{
			fontNameList.add(new FontNameModel(fontName, fontName));
		}

		ListWidgetModel<FontNameModel> fontNameListModel = new ListWidgetModel<FontNameModel>(fontNameList,
				new String[] { "name" });
		this.fontListWidget.setListModel(fontNameListModel);

		// init font style
		List<FontStyleModel> fontStyleList = new ArrayList<FontStyleModel>();
		fontStyleList.add(new FontStyleModel("Plain", Font.PLAIN));
		fontStyleList.add(new FontStyleModel("Bold", Font.BOLD));
		fontStyleList.add(new FontStyleModel("Italic", Font.ITALIC));
		fontStyleList.add(new FontStyleModel("Bold Italic", Font.BOLD | Font.ITALIC));

		ListWidgetModel<FontStyleModel> fontStyleListModel = new ListWidgetModel<FontStyleModel>(fontStyleList,
				new String[] { "name" });
		this.fontStyleListWidget.setListModel(fontStyleListModel);

		// init font size
		int[] fontSizes = new int[] { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48 };
		List<FontSizeModel> fontSizeList = new ArrayList<FontSizeModel>();
		for (int fontSize : fontSizes)
		{
			fontSizeList.add(new FontSizeModel(String.valueOf(fontSize), fontSize));
		}

		ListWidgetModel<FontSizeModel> fontSizeListModel = new ListWidgetModel<FontSizeModel>(fontSizeList,
				new String[] { "name" });
		this.fontSizeListWidget.setListModel(fontSizeListModel);
	}

	/**
	 * Set font
	 */
	public void setDefaultFont(Font font)
	{
		if (font == null)
			throw new IllegalArgumentException("Font can not be null!");

		this.font = font;

		renderFontName(this.font.getName());
		renderFontStyle(this.font.getStyle());
		renderFontSize(this.font.getSize());

		repaintSample();
	}

	/**
	 * Get font
	 */
	public Font getSelectedFont()
	{
		return this.font;
	}

	/**
	 * Render textbox and list of font name
	 * @param fontName
	 */
	private void renderFontName(String fontName)
	{
		FontNameModel fontNameModel = forFontName(fontName);
		this.fontListWidget.setSelected(fontNameModel);
		this.fontTextBox.setText(fontName);
	}

	/**
	 * Render textbox and list of font style
	 * @param fontStyle
	 */
	private void renderFontStyle(int fontStyle)
	{
		FontStyleModel fontStyleModel = forFontStyle(fontStyle);
		this.fontStyleListWidget.setSelected(fontStyleModel);
		this.fontStyleTextBox.setText(fontStyleModel.getName());
	}

	/**
	 * Render textbox and list of font size
	 * @param fontSize
	 */
	private void renderFontSize(int fontSize)
	{
		FontSizeModel fontSizeModel = forFontSize(fontSize);
		if (fontSizeModel != null)
			this.fontSizeListWidget.setSelected(fontSizeModel);
		this.fontSizeTextBox.setText(String.valueOf(fontSize));
	}

	/**
	 * Repaint Sample with the given font
	 */
	private void repaintSample()
	{
		this.sampleTextField.setFont(this.font);
	}

	/**
	 * Get the fontNameModel by the given fontname
	 * 
	 * @param fontName
	 * @return
	 */
	private FontNameModel forFontName(String fontName)
	{
		FontNameModel ret = null;
		List<FontNameModel> fontNameList = this.fontListWidget.getListModel().getEntries();
		for (FontNameModel fontNameModel : fontNameList)
		{
			if (fontNameModel.getValue().equalsIgnoreCase(fontName) || fontNameModel.getName().equalsIgnoreCase(fontName))
				ret = fontNameModel;
		}

		return ret;
	}

	/**
	 * Get the FontStyleModel by the given fontstyle
	 * 
	 * @param fontName
	 * @return
	 */
	private FontStyleModel forFontStyle(int fontStyle)
	{
		FontStyleModel ret = null;
		List<FontStyleModel> fontStyleList = this.fontStyleListWidget.getListModel().getEntries();
		for (FontStyleModel fontStyleModel : fontStyleList)
		{
			if (fontStyleModel.getValue() == fontStyle)
				ret = fontStyleModel;
		}

		return ret;
	}

	/**
	 * Get the FontStyleModel by the given fontstyle
	 * 
	 * @param fontName
	 * @return
	 */
	private FontSizeModel forFontSize(int fontSize)
	{
		FontSizeModel ret = null;
		List<FontSizeModel> fontStyleList = this.fontSizeListWidget.getListModel().getEntries();
		for (FontSizeModel fontSizeModel : fontStyleList)
		{
			if (fontSizeModel.getValue() == fontSize || fontSizeModel.getName().equals(String.valueOf(fontSize)))
				ret = fontSizeModel;
		}

		return ret;
	}

	/**
	 * Get font style by the given name
	 * @param fontStyleName
	 * @return
	 */
	private int getFontStyle(String fontStyleName)
	{
		if ("Plain".equalsIgnoreCase(fontStyleName))
			return Font.PLAIN;
		else if ("Bold".equalsIgnoreCase(fontStyleName))
			return Font.BOLD;
		else if ("Italic".equalsIgnoreCase(fontStyleName))
			return Font.ITALIC;
		else if ("Bold Italic".equalsIgnoreCase(fontStyleName))
			return Font.BOLD | Font.ITALIC;
		else
			return UNKNOWN_FONT_STYLE;
	}

	// Model class
	class FontNameModel
	{
		private String name;
		private String value;

		public FontNameModel(String name, String value)
		{
			this.value = value;
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getValue()
		{
			return value;
		}

		public void setValue(String value)
		{
			this.value = value;
		}
	}

	class FontStyleModel
	{
		private String name;
		private int value;

		public FontStyleModel(String name, int value)
		{
			this.value = value;
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
		}
	}

	class FontSizeModel
	{
		private String name;
		private int value;

		public FontSizeModel(String name, int value)
		{
			this.value = value;
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
		}
	}
}
