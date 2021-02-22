package calculator.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager2;

public class CalcLayout implements LayoutManager2 {

	private int hgap;
	private int vgap;
	
	private Component comp1;
	private Component comp2;
	private Component comp3;
	private Component comp4;
	
	public static final String COMP1 = "comp1";
	public static final String COMP2 = "comp2";
	public static final String COMP3 = "comp3";
	public static final String COMP4 = "comp4";
        
	public CalcLayout() {
		hgap = 0;
		vgap = 0;
	}
	
	public CalcLayout(int hgap, int vgap) {
		this.hgap = hgap;
		this.vgap = vgap;
	}
	
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if( constraints == null || constraints instanceof String ) {
			String name = (String)constraints;
			
			if( constraints == null ) constraints = COMP4;
			else {
				if( COMP1.equals(name) ) comp1 = comp;
				else if( COMP2.equals(name) ) comp2 = comp;
				else if( COMP3.equals(name) ) comp3 = comp;
				else if( COMP4.equals(name) ) comp4 = comp;
			}
		} else {
		    throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
		}		
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		// TODO Auto-generated method stub
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		// TODO Auto-generated method stub
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return null;
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			
			int hComp1 = comp1 != null ? comp1.getPreferredSize().height : 0;
			
			Insets insets = parent.getInsets();
			int top = insets.top + vgap;
			int bottom = parent.getHeight()- insets.bottom - vgap;
			int left = insets.left + hgap;
			int right = parent.getWidth() - insets.right - hgap;

	        if ( comp1 != null) {
	    	    comp1.setBounds(left, top, right-hgap, hComp1);
	    	    comp1.setFont(comp1.getFont().deriveFont(Font.PLAIN));
	    	    top += hComp1 + vgap;
	        }
	        if ( comp2 != null) {
	    	    comp2.setBounds(left, top, right/5, bottom - hComp1 - vgap*2);
	    	    left += right/5 + hgap;
	        }
	        if ( comp3 != null) {
	    	    comp3.setBounds(left, top, right-left, bottom/5 - vgap);
	    	    top += bottom/5;
	        }
	        if ( comp4 != null) {
	    	    comp4.setBounds(left, top, right-left, (bottom/5)*4  - hComp1- vgap*2);
	        }	        
		}
    }

	@Override
	public Dimension minimumLayoutSize(Container parent) {
    	synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension(0, 0);

			int wC1 = comp1 == null ? hgap : comp1.getMinimumSize().width;
			int wC2 = comp2 == null ? hgap : comp2.getMinimumSize().width + hgap*2;
			int wC3 = comp3 == null ? hgap : comp3.getMinimumSize().width + hgap*4;
			int wC4 = comp4 == null ? hgap : comp4.getMinimumSize().width + hgap*2;
			
			int hC1 = comp1 == null ? vgap : comp1.getMinimumSize().height;
			int hC2 = comp2 == null ? vgap : comp2.getMinimumSize().height + vgap*2;
			int hC3 = comp3 == null ? vgap : comp3.getMinimumSize().height + vgap*2;
			int hC4 = comp4 == null ? vgap : comp4.getMinimumSize().height + vgap*2;
			
			dim.width = Math.max(Math.max(wC1, wC2+wC3), wC2+wC4) + hgap*2;
			dim.height = Math.max(hC1+hC2, hC1+hC3+hC4) + vgap*2;

			Insets insets = parent.getInsets();
			dim.width += insets.left + insets.right;
			dim.height += insets.top + insets.bottom;
			
			return dim;
		}
	}
   
    @Override
	public Dimension preferredLayoutSize(Container parent) {
    	synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension(0, 0);

			int wC1 = comp1 == null ? hgap : comp1.getPreferredSize().width;
			int wC2 = comp2 == null ? hgap : comp2.getPreferredSize().width + hgap*2;
			int wC3 = comp3 == null ? hgap : comp3.getPreferredSize().width + hgap*4;
			int wC4 = comp4 == null ? hgap : comp4.getPreferredSize().width + hgap*2;
			
			int hC1 = comp1 == null ? vgap : comp1.getPreferredSize().height;
			int hC2 = comp2 == null ? vgap : comp2.getPreferredSize().height + vgap*2;
			int hC3 = comp3 == null ? vgap : comp3.getPreferredSize().height + vgap*2;
			int hC4 = comp4 == null ? vgap : comp4.getPreferredSize().height + vgap*2;
			
			dim.width = Math.max(Math.max(wC1, wC2+wC3), wC2+wC4) + hgap*2;
			dim.height = Math.max(hC1+hC2, hC1+hC3+hC4) + vgap*2;

			Insets insets = parent.getInsets();
			dim.width += insets.left + insets.right;
			dim.height += insets.top + insets.bottom;
			
			return dim;
		}
	}	

	@Override
	public void removeLayoutComponent(Component comp) {
		if( comp == comp1 ) comp1 = null;
		else if( comp == comp2 ) comp2 = null;
		else if( comp == comp3 ) comp3 = null;
		else if( comp == comp4 ) comp4 = null;
	}
}