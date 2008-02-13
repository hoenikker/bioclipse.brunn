package net.bioclipse.brunn.ui.explorer.model.nonFolders;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import net.bioclipse.brunn.Springcontact;
import net.bioclipse.brunn.business.origin.IOriginManager;
import net.bioclipse.brunn.business.sample.ISampleManager;
import net.bioclipse.brunn.pojos.DrugOrigin;
import net.bioclipse.brunn.ui.Activator;
import net.bioclipse.dialogs.SaveAsDialog;
import net.bioclipse.brunn.ui.explorer.model.nonFolders.AbstractNonFolder;
import net.bioclipse.brunn.ui.explorer.model.ITreeObject;
import net.bioclipse.brunn.ui.images.IconFactory;
import net.bioclipse.model.BioResourceType;
import net.bioclipse.model.IBioResource;
import net.bioclipse.model.IBioResourceFormat;
import net.bioclipse.model.IPersistedResource;

public class Compound extends AbstractNonFolder implements IEditorInput {

	public Compound(ITreeObject parent, DrugOrigin drugOrigin) {
		
		super(parent, drugOrigin);
	}

	public DrugOrigin getDrugOrigin() {
		return (DrugOrigin)object;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public Image getIconImage() {
		return IconFactory.getImage("molecule16.gif");
	}

	public void changeName(String name) {
		object.setName(name);
		( (IOriginManager) Springcontact.getBean("originManager") ).edit(Activator.getDefault().getCurrentUser(), (DrugOrigin)object);	
	}

	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		return "Compound";
	}


}
