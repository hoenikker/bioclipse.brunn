package net.bioclipse.brunn.ui.editors.plateEditor;

import net.bioclipse.brunn.ui.editors.plateEditor.model.IC50TableModel;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.SWTX;

public class IC50 extends EditorPart implements OutlierChangedListener {

	private KTable table;
	private IC50TableModel tableModel;
	private Replicates replicates;
	
	private final Clipboard cb = new Clipboard(
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay() );
	
		
	public IC50(PlateMultiPageEditor plateMultiPageEditor, Replicates replicates) {
		super();
		plateMultiPageEditor.addListener(this);
		this.replicates = replicates;
		tableModel = new IC50TableModel(replicates);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new FillLayout());

		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		table = new KTable( composite, SWTX.MARK_FOCUS_HEADERS |
                                       SWTX.AUTO_SCROLL        |
                                       SWT.MULTI );
		final FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, -5);
		formData.top = new FormAttachment(0, 0);
		table.setLayoutData(formData);
		
		table.setModel(tableModel);

		Button copyTableToButton;
		copyTableToButton = new Button(composite, SWT.NONE);
		copyTableToButton.addSelectionListener(new SelectionAdapter() {
			/*
			 * COPY TABLE BUTTON
			 */
			public void widgetSelected(SelectionEvent e) {
				
				IC50TableModel model = (IC50TableModel)table.getModel();
				StringBuilder stringBuilder = new StringBuilder();
				for( int row = 0 ; row < model.getRowCount() ; row++) {
					for( int col = 0 ; col < model.getColumnCount() ; col++) {
						stringBuilder.append( model.doGetContentAt(col, row) ); 
						stringBuilder.append('\t');
					}
					stringBuilder.append('\n');
				}
				
				TextTransfer textTransfer = TextTransfer.getInstance();
				Transfer[] types = new Transfer[] {textTransfer};
				cb.setContents( new Object[]{ stringBuilder.toString() }, types );
			}
		});
		formData.bottom = new FormAttachment(copyTableToButton, -5, SWT.TOP);
		final FormData formData_1 = new FormData();
		formData_1.bottom = new FormAttachment(100, -8);
		formData_1.left = new FormAttachment(0, 5);
		copyTableToButton.setLayoutData(formData_1);
		copyTableToButton.setText("Copy Table to Clipboard");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		super.dispose();
		cb.dispose();
	}

	@Override
	public void onOutLierChange() {
		tableModel = new IC50TableModel(replicates);
		table.setModel( tableModel );
	}

}
