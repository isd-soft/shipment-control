import {Directive, Input, TemplateRef, ViewContainerRef} from '@angular/core';

@Directive({
  selector: '[appHideForRoles]'
})
export class HideForRolesDirective {

  constructor(
      private viewConttainerRef: ViewContainerRef,
      private templateRef: TemplateRef<any>,
  ) { }


  @Input() set(hideForRoles: Array<string>){
    const hideFor = hideForRoles || [];
    if (hideFor.length > 0){
      this.roleChecker(hideFor);
    } else {
      this.viewConttainerRef.createEmbeddedView(this.templateRef);
    }
  }

  roleChecker(hideFor: Array<string>){
    //current user roles
    const userRoles: Array<string> = ['SHIPMENT_COMPANY', 'GOODS_COMPANY'];

    if (userRoles.length === 0){
      this.viewConttainerRef.clear();
    } else {
      const index = userRoles.findIndex(role => hideFor.indexOf(role) !== -1);
      return index < 0 ? this.viewConttainerRef.createEmbeddedView(this.templateRef) : this.viewConttainerRef.clear();
    }
  }

}
