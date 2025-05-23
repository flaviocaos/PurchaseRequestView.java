package br.com.firsti.packages.purchase.modules.purchaseRequest.actions;

import static br.com.firsti.packages.stock.common.StockReferences.AVAILABILITY_DATE;
import static br.com.firsti.packages.stock.common.StockReferences.CANCELLATION_DATE;
import static br.com.firsti.packages.stock.common.StockReferences.CANCELLATION_REQUEST;
import static br.com.firsti.packages.stock.common.StockReferences.CATEGORY;
import static br.com.firsti.packages.stock.common.StockReferences.COLLABORATOR;
import static br.com.firsti.packages.stock.common.StockReferences.DIVISION;
import static br.com.firsti.packages.stock.common.StockReferences.LOT;
import static br.com.firsti.packages.stock.common.StockReferences.MANUFACTURER;
import static br.com.firsti.packages.stock.common.StockReferences.MODEL;
import static br.com.firsti.packages.stock.common.StockReferences.NAME;
import static br.com.firsti.packages.stock.common.StockReferences.NUMBER;
import static br.com.firsti.packages.stock.common.StockReferences.PATRIMONY;
import static br.com.firsti.packages.stock.common.StockReferences.PICTURE;

import java.awt.TextArea;

import static br.com.firsti.packages.stock.common.StockReferences.*;

import br.com.firsti.languages.LRCore;
import br.com.firsti.languages.LRProduct;
import br.com.firsti.languages.LRPurchase;
import br.com.firsti.languages.LRStock;
import br.com.firsti.module.actions.AbstractActionView;
import br.com.firsti.module.exceptions.InternalServerErrorException;
import br.com.firsti.module.exceptions.PermissionDeniedException;
import br.com.firsti.module.exceptions.ResourceNotFoundException;
import br.com.firsti.module.requests.ActionRequest;
import br.com.firsti.packages.organization.entities.Collaborator;
import br.com.firsti.packages.organization.entities.Company;
import br.com.firsti.packages.product.entities.Product;
import br.com.firsti.packages.product.entities.ProductCategory;
import br.com.firsti.packages.purchase.entities.Purchase;
import br.com.firsti.packages.purchase.entities.PurchaseRequest;
import br.com.firsti.packages.purchase.modules.purchaseRequest.ModulePurchaseRequest;
import br.com.firsti.packages.stock.entities.Warehouse;
import br.com.firsti.packages.stock.modules.stockMovement.actions.StockMovementView;
import br.com.firsti.persistence.EntityManagerWrapper;
import br.com.firsti.services.websocket.messages.output.elements.DataFormat;
import br.com.firsti.services.websocket.messages.output.elements.items.Button;
import br.com.firsti.services.websocket.messages.output.elements.ElementRequest;
import br.com.firsti.services.websocket.messages.output.elements.items.ElementGroup;
import br.com.firsti.services.websocket.messages.output.elements.items.InputView;
import br.com.firsti.services.websocket.messages.output.elements.items.TableButton;
import br.com.firsti.services.websocket.messages.output.elements.items.Textarea;
import br.com.firsti.services.websocket.messages.output.elements.items.TableButton.TableButtonType;

public class PurchaseRequestView extends AbstractActionView<ModulePurchaseRequest> {
public PurchaseRequestView() {
	super(new Builder<>(Access.COMPANY_PRIVATE));
}

	@Override
	public void onWindowRequest(EntityManagerWrapper entityManager, ActionRequest request, WindowBuilder windowBuilder)
			throws PermissionDeniedException, ResourceNotFoundException, InternalServerErrorException {

		// TODO Auto-generated method stub
		
		PurchaseRequest purchaseRequest = entityManager.find(PurchaseRequest.class, request.getEntityId());
		Collaborator requester = request.getUserProfile().getCollaborator();
		
		
		if (purchaseRequest == null) {
			throw new ResourceNotFoundException();
			
		}
		
		// Preencher dados
		windowBuilder.getDataBuilder()
				.add("company", purchaseRequest.getWarehouse().getCompany().getName())
				.add("warehouse", purchaseRequest.getWarehouse().getName())
				.add("status", purchaseRequest.getStatus())
				.add("requester", purchaseRequest.getRequester().getName())
				.add("creation", purchaseRequest.getCreation())
				.add("category", purchaseRequest.getProductType() != null ? purchaseRequest.getProductType().getCategory() : null)
				.add("productType", purchaseRequest.getProductType()!= null ? purchaseRequest.getProductType().getName() : null)
                .add("productName", purchaseRequest.getProduct()!= null ? purchaseRequest.getProduct().getModel() : null)
                .add("quantity", purchaseRequest.getQuantity())
                .add("description", purchaseRequest.getDescription());
		
		// Header
        windowBuilder.getHeaderBuilder()
                .add(new InputView("company").setLabel(LRCore.COMPANY).addClass("col-4"))
                .add(new InputView("warehouse").setLabel(LRCore.WAREHOUSE).addClass("col-6"))
                .add(new InputView("status").setLabel(LRCore.STATUS).setTranslate(LRPurchase.class).addClass("col-2"))

                .add(new InputView("requester").setLabel(LRCore.REQUESTER).addClass("col-10"))
                .add(new InputView("creation").setLabel(LRCore.CREATION).setFormat(DataFormat.DATETIME).addClass("col-2"));
        
     // Body
        windowBuilder.getBodyBuilder()
                .add(new ElementGroup("productInfo").setLabel(LRProduct.PRODUCT).addClass("col")
                        .add(new InputView("category").setLabel(LRProduct.CATEGORY).addClass("col-2"))
                        .add(new InputView("productType").setLabel(LRProduct.PRODUCT_TYPE).addClass("col-4"))
                        .add(new InputView("productName").setLabel(LRProduct.PRODUCT).addClass("col-4"))
                        .add(new InputView("quantity").setLabel(LRCore.QUANTITY).addClass("col-2").addInputClass("text-right"))
                        		
                			
                .add(new Textarea("description")
                        .setLabel(LRCore.DESCRIPTION)
                        .setMinHeight(100)
                        .addClass("col-12")));  
        
        	            
        if (purchaseRequest.getPurchase()!= null) {
	        windowBuilder.getFooterBuilder()
	        	.add(new Button("viewPurchase", LRPurchase.VIEW_PURCHASE)
	        		.setOnClick(ElementRequest.createModalRequest(StockMovementView.class).setEntityId(purchaseRequest.getPurchase().getId())));
        }
        	
        windowBuilder.getFooterBuilder()
            .add(new Button("editPurchase", LRCore.EDIT)
                .setOnClick(ElementRequest.createPopupRequest(PurchaseRequestEdit.class).setEntityId(request.getEntityId())));     
      
       
       
        
                        
 
        }
	}

