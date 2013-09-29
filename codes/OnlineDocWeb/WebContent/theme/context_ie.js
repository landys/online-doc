/*********************************************************** {COPYRIGHT-TOP} ***
* Licensed Materials - Property of IBM                                               
* Tivoli Presentation Services                                   
*                                                    
* (C) Copyright IBM Corp. 2002,2003 All Rights Reserved.                            
*                                                               
* US Government Users Restricted Rights - Use, duplication or
* disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
************************************************************ {COPYRIGHT-END} ***
* Change Activity on 4/29/03 version 1.13:
* @00=WCL, V3R0, 04/14/2002, JCP: Initial version
* @01=D96484, V3R2, 06/14/2002, bcourt: hide select/iframe elements
* @02=D99598, V3R2, 07/02/2002, JCP: IE6 has different body size than IE5
* @03=D104656, V3R3, 09/16/2002, JCP: form submit instead of triggers
* @04=D107029, V3R4, 12/03/2002, Mark Rebuck:  Added support for timed menu hiding
* @05=D113641, V3R4, 04/28/2003, LSR: Requirement #258 Shorten CSS Names
*******************************************************************************/

var visibleMenu_ = null;
var padding_ = 10;

var transImg_ = "transparent.gif";

var arrowNorm_ = "contextArrowDefault.gif";
var arrowSel_ = "contextArrowSelected.gif";
var arrowDis_ = "contextArrowDisabled.gif";
var launchNorm_ = "contextLauncherDefault.gif";
var launchSel_ = "contextLauncherSelected.gif";

var arrowNormRTL_ = "contextArrowDefault.gif";
var arrowSelRTL_ = "contextArrowSelected.gif";
var arrowDisRTL_ = "contextArrowDisabled.gif";
var launchNormRTL_ = "contextLauncherDefault.gif";
var launchSelRTL_ = "contextLauncherSelected.gif";

var arrowWidth_ = "12";
var arrowHeight_ = "12";

var submenuAltText_ = "+";
var noActionsText_ = "(0)";

var hideCurrentMenuTimer_ = null;

var onmousedown_ = document.onmousedown;

function clearMenuTimer( ) {
   if (null != hideCurrentMenuTimer_) {
      clearTimeout( hideCurrentMenuTimer_ );
      hideCurrentMenuTimer_ = null;
   }
}

function setMenuTimer( ) {
   clearMenuTimer( );
   hideCurrentMenuTimer_ = setTimeout( 'hideCurrentContextMenu( )', 2000);
}

function debug( str ) {
   /*
   if ( xbDEBUG != null ) {
      xbDEBUG.dump( str );
   }
   */
}

// constructor
function UilContextMenu( name, isLTR, width ) {
   // member variables
   this.name = name;
   this.items = new Array();
   this.isVisible = false;
   this.isDismissable = true;
   this.selectedItem = null;
   this.isDynamic = false;
   this.isCacheable = false;
   this.isEmpty = true;
   this.isLTR = isLTR;
   this.hiddenItems = new Array(); //@01A
   this.isHyperlinkChild = true;  //  We will reset later if needed.

   // html variables
   this.launcher = null;
   this.menuTag = null;

   // external methods
   this.add = UilContextMenuAdd;
   this.addSeparator = UilContextMenuAddSeparator;
   this.show = UilContextMenuShow;
   this.hide = UilContextMenuHide;

   // internal methods
   this.create = UilContextMenuCreate;
   this.getMenuItem = UilContextMenuGetMenuItem;
   this.getSelectedItem = UilContextMenuGetSelectedItem;

   if ( this.name == null ) {
      this.name = "UilContextMenu_" + allMenus_.length;
   }
}

// adds a menu item to the context menu
function UilContextMenuAdd( item ) {
   this.items[ this.items.length ] = item;
   this.isEmpty = false;
}

function UilContextMenuAddSeparator() {
   var sep = new UilMenuItem();
   sep.isSeparator = true;
   this.add( sep );
}

// shows the context menu
// launcher- html element (anchor) that is launching the menu
// launchItem- menu item that is launching the menu
function UilContextMenuShow( launcher, launchItem ) {
   if ( this.items.length == 0 ) {
      // empty context menu
      debug( 'menu is empty!' );
      this.add( new UilMenuItem( noActionsText_, false, "javascript:void(0);" ) );
      this.isEmpty = true;
   }

   if ( this.menuTag == null ) {
      // create the context menu html
      this.create();
   }

   if ( this.menuTag != null) {
      // store the launcher for later
      this.launcher = launcher;
      if ( this.launcher.tagName == "IMG" ) {
         this.isHyperlinkChild = false;

         // we want the anchor tag
         this.launcher = this.launcher.parentElement;
      }

      // boundaries of window
      // @02 - minor difference between IE5 and IE6 to determine available space
      var maxX = document.documentElement.scrollLeft + document.documentElement.clientWidth;
      var maxY = document.documentElement.scrollTop + document.documentElement.clientHeight;
      var maxX2 = document.body.scrollLeft + document.body.clientWidth;
      var maxY2 = document.body.scrollTop + document.body.clientHeight;
      if ( maxX2 > maxX ) maxX = maxX2;
      if ( maxY2 > maxY ) maxY = maxY2;
      var menuWidth = getWidth( this.menuTag );
      var menuHeight = getHeight( this.menuTag );

      // move the context menu to the right of the launcher
      var posX = 0;
      var posY = 0;
      if ( launchItem != null ) {
         // launched from submenu
         var launchTag = launchItem.itemTag;
         var launchTagWidth = getWidth( launchTag );
         var parentTag = launchItem.parentMenu.menuTag;
         var parentOffsetX = getLeft( parentTag );
         var parentOffsetY = getTop( parentTag );

         posX = parentOffsetX + getLeft( launchTag ) + launchTagWidth;
         posY = parentOffsetY + getTop( launchTag );

         if ( !this.isLTR ) {
            posX -= launchTagWidth;
            posX -= menuWidth;
         }

         // try to keep it in the window
         if ( this.isLTR ) {
            if ( posX + menuWidth > maxX ) {
               // try to show it to the left of the parent menu
               var posX1 = parentOffsetX - menuWidth;
               var posX2 = maxX - menuWidth;
               if ( 0 <= posX1 ) {
                  posX = posX1;
               }
               else {
                  posX = Math.max( 0, posX2 );
               }
            }
         }
         else {
            if ( posX < 0 ) {
               // try to show it to the right of the parent menu
               var posX1 = parentOffsetX + getWidth( parentTag );
               if ( posX1 + menuWidth < maxX ) {
                  posX = posX1;
               }
               else {
                  posX = Math.min( 0, maxX - menuWidth );
               }
            }
         }

         if ( posY + menuHeight > maxY ) {
            var posY1 = maxY - menuHeight;
            posY = Math.max( 0, posY1 );
         }
      }
      else {
         // launched from menu link
         var launcherLeft = getLeft( this.launcher, true );
         if ( this.launcher.tagName == "BUTTON" ) {
             posX = launcherLeft;
             
             // bidi
             if ( !this.isLTR ) {
                 posX += getWidth( this.launcher ) - getWidth( this.menuTag );
             }
             
             posY = getTop( this.launcher, true );
             if ( posY + menuHeight > maxY ) {
                // top
                posY -= menuHeight;
             }
             else {
                // bottom
                posY += getHeight( this.launcher );
             }
         }
         else {
             // left-right
             posX = launcherLeft + this.launcher.offsetWidth;
             posY = getTop( this.launcher, true );

             if ( !this.isLTR ) {
                posX -= this.launcher.offsetWidth;
                posX -= menuWidth;
             }

             // keep it in the window
             if ( this.isLTR ) {
                if ( posX + menuWidth > maxX ) {
                   // try to show it on the left side of the launcher
                   var posX1 = launcherLeft - menuWidth;
                   if ( posX1 > 0 ) {
                      posX = posX1;
                   }
                   else {
                      posX = Math.max( 0, maxX - menuWidth );
                   }
                }
             }
             else {
                if ( posX < 0 ) {
                   // try to show it on the right side of the launcher
                   var posX1 = launcherLeft + this.launcher.offsetWidth;
                   if ( posX1 + menuWidth < maxX ) {
                      posX = posX1;
                   }
                   else {
                      posX = Math.min( 0, maxX - menuWidth );
                   }
                }
             }

             if ( posY + menuHeight > maxY ) {
                posY = Math.max( 0, maxY - menuHeight );
             }
         }
      }

      debug( 'show ' + this.name + ': ' + posX + ', ' + posY );
      this.menuTag.style.left = posX + "px";
      this.menuTag.style.top = posY + "px";

      // make the context menu visible
      this.menuTag.style.visibility = "visible";
      this.isVisible = true;

      // set focus on the first menu item
      this.items[0].anchorTag.focus();

      // @01A - Hide any items that intersect this menu
      var coll = document.getElementsByTagName("SELECT");
      if (coll!=null)
      {
         for (i=0; i<coll.length; i++)
         {
            //Hide the element
            if (intersect(this.menuTag,coll[i]) == true ) {
               if (coll[i].style.visibility != "hidden")
               {
                  coll[i].style.visibility = "hidden";
                  this.hiddenItems.push(coll[i]);
               }
            }
         }
      }
      coll = document.getElementsByTagName("IFRAME");
      if (coll!=null)
      {
         for (i=0; i<coll.length; i++)
         {
            //Hide the element
            if (intersect(this.menuTag,coll[i]) == true ) {
               if (coll[i].style.visibility != "hidden")
               {
                  coll[i].style.visibility = "hidden";
                  this.hiddenItems.push(coll[i]);
               }
            }
         }
      }

      // save old & set new hide action for this menu     
      onmousedown_ = document.onmousedown;
      document.onmousedown = hideCurrentContextMenu;
   }
}

// Test whether two objects overlap
function intersect(obj1, obj2) //@01A
{
   var rect1 = obj1.getBoundingClientRect();
   var rect2 = obj2.getBoundingClientRect();
   if (lineIntersect(rect1.top, rect1.bottom, rect2.top, rect2.bottom) &&
       lineIntersect(rect1.left, rect1.right, rect2.left, rect2.right) ) {
      return true;
   }
   return false;
}

// Test whether the two line segments a--b and c--d intersect.
function lineIntersect(a, b, c, d) //@01A
{
   //alert (a+"--"+b + "   " + c + "--" + d);
   //Assume that a < b && c < d
   if ( (a <= c  && c <= b) ||
        (a <= d && d <= b) ||
        (c <= a && d >= b ) )
   {
      return true;
   } else {
      return false;
   }
}

// hides the context menu
function UilContextMenuHide() {
   if ( this.menuTag != null ) {
      debug( 'hide ' + this.name );

      // hide any visible submenus first
      for ( var i=0; i<this.items.length; i++ ) {
         if ( this.items[i].submenu != null &&
              this.items[i].submenu.isVisible ) {
            this.items[i].submenu.hide();
         }
      }

      // clear selection
      if ( this.selectedItem != null ) {
         this.selectedItem.setSelected( false );
      }

      // make the context menu hidden
      this.menuTag.style.visibility = "hidden";
      this.isVisible = false;
      this.isDismissable = true;

      // @01A - Show any items that were hidden by this menu
      var itemCount = this.hiddenItems.length;
      for (i=0; i<itemCount; i++)
      {
         var item = this.hiddenItems.pop();
         item.style.visibility = "visible";
      }

      // clear the launcher
      this.launcher = null;

      // reset action      
      document.onmousedown = onmousedown_;
   }
}

// creates the context menu html element
function UilContextMenuCreate( recurse ) {
   if ( this.menuTag == null ) {
      this.menuTag = document.createElement( "DIV" );
      this.menuTag.style.position = "absolute";
      this.menuTag.style.cursor = "default";
      this.menuTag.style.visibility = "hidden";
      this.menuTag.style.width = "0px"; // this causes dynamic sizing
      this.menuTag.onmouseover = contextMenuDismissDisable;
      this.menuTag.onmouseout = contextMenuDismissEnable;
      this.menuTag.oncontextmenu = contextMenuOnContextMenu;

      var numItems = this.items.length;

      // check if this context menu contains icons or submenus
      var hasIcon = false;
      var hasSubmenu = false;
      for ( var i=0; i<numItems; i++ ) {
         if ( !this.items[i].isSeparator ) {
            if ( !hasSubmenu && this.items[i].submenu != null ) {
               hasSubmenu = true;
            }
            if ( !hasIcon && this.items[i].icon != null ) {
               hasIcon = true;
            }
            if ( hasSubmenu && hasIcon ) {
               break;
            }
         }
      }

      // create the menu items
      for ( var i=0; i<numItems; i++ ) {
         this.items[i].isFirst = ( i == 0 );
         this.items[i].isLast = ( i+1 == numItems );
         this.items[i].parentMenu = this;
         this.items[i].create( hasIcon, hasSubmenu );
      }

      // create the context menu border
      var border = document.createElement( "TABLE" );
      if (!this.isLTR) border.dir = "RTL";
      //border.className = "pop2"; //@05C1
      border.className = "lwpShadowBorder"; 
      border.rules = "none";
      border.cellPadding = 0;
      border.cellSpacing = 0;
      border.width = "100%";
      border.border = 0;
      var borderBody = document.createElement( "TBODY" );
      var borderRow = document.createElement( "TR" );
      var borderData = document.createElement( "TD" );
      borderRow.appendChild( borderData );
      borderBody.appendChild( borderRow );
      border.appendChild( borderBody );

      // create the context menu
      var table = document.createElement( "TABLE" );
      if (!this.isLTR) table.dir = "RTL";
      //table.className = "pop1";  //@05C1
      table.className = "lwpBorderAll";  //@05C1
      table.rules = "none";
      table.cellPadding = 3;
      table.cellSpacing = 0;
      table.width = "100%";
      table.border = 0;

      // add the menu items
      var tableBody = document.createElement( "TBODY" );
      table.appendChild( tableBody );
      for ( var i=0; i<numItems; i++ ) {
         if ( this.items[i].isSeparator ) {
            this.items[i].createSeparator( tableBody, hasSubmenu );
         }
         else {
            tableBody.appendChild( this.items[i].itemTag );
         }
      }
      borderData.appendChild( table );
      this.menuTag.appendChild( border );

      // add to document
      document.body.appendChild( this.menuTag );
   }

   if ( recurse ) {
      // this is used when cloning dynamic menus
      for ( var i=0; i<this.items.length; i++ ) {
         if ( this.items[i].submenu != null ) {
            this.items[i].submenu.create( recurse );
         }
      }
   }
}

// returns the menu item object associated with the html element
function UilContextMenuGetMenuItem( htmlElement ) {
   if ( htmlElement != null ) {
      for ( var i=0; i<this.items.length; i++ ) {
         if ( this.items[i].itemTag != null &&
              this.items[i].itemTag.contains( htmlElement ) ) {
            // found the item
            return this.items[i];
         }
         else if ( this.items[i].submenu != null &&
                   this.items[i].submenu.isVisible ) {
            // recurse through any visible submenus
            var item = this.items[i].submenu.getMenuItem( htmlElement );
            if ( item != null ) {
               // found the item
               return item;
            }
         }
      }
   }
   return null;
}

// returns the deepest selected menu item
function UilContextMenuGetSelectedItem() {
   var item = this.selectedItem;
   if ( item != null && item.submenu != null && item.submenu.isVisible ) {
      // recurse through the item's visible submenu
      return item.submenu.getSelectedItem();
   }
   return item;
}

// method called by an event handler (onmouseover for context menu div)
function contextMenuDismissEnable() {
   if ( visibleMenu_ != null ) {
      visibleMenu_.isDismissable = true;
      if (visibleMenu_.isHyperlinkChild) {
         setMenuTimer( );
      }
   }
}

// method called by an event handler (onmouseout for context menu div)
function contextMenuDismissDisable() {
   if ( visibleMenu_ != null ) {
      visibleMenu_.isDismissable = false;
      clearMenuTimer( );
   }
}

// method called by an event handler (oncontextmenu for context menu div)
function contextMenuOnContextMenu() {
   return false;
}

// constructor
function UilMenuItem( text, enabled, action, clientAction, submenu, icon, defItem, menuStyle, selectedMenuStyle ) {
   // member variables
   this.text = text;
   this.icon = icon;
   this.action = action;
   this.clientAction = clientAction;
   this.submenu = submenu;
   this.isSeparator = false;
   this.isSelected = false;
   this.isEnabled = ( enabled != null ) ? enabled : true;
   this.isDefault = ( defItem != null ) ? defItem : false;
   this.isFirst = false;
   this.isLast = false;
   this.parentMenu = null;
   
   if ( menuStyle != null ) {
      this.menuStyle = menuStyle; 
   }
   else {
      this.menuStyle = ( this.isEnabled ) ? "lwpMenuItem" : "lwpMenuItemDisabled"; 
   }
   
   this.selectedMenuStyle = ( selectedMenuStyle != null) ? selectedMenuStyle : "lwpSelectedMenuItem";

   // html variables
   this.itemTag = null;
   this.anchorTag = null;
   this.arrowTag = null;

   // internal methods
   this.create = UilMenuItemCreate;
   this.createSeparator = UilMenuItemCreateSeparator;
   this.setSelected = UilMenuItemSetSelected;
   this.updateStyle = UilMenuItemUpdateStyle;
   this.getNextItem = UilMenuItemGetNextItem;
   this.getPrevItem = UilMenuItemGetPrevItem;

   if ( this.submenu != null ) {
      // menu items with submenus do not have actions
      this.action = null;
   }
}

// creates the menu item html element
function UilMenuItemCreate( menuHasIcon, menuHasSubmenu ) {
   if ( !this.isSeparator ) {
      this.anchorTag = document.createElement( "A" );
      if ( this.action != null ) {
         this.anchorTag.href = "javascript:menuItemLaunchAction();";
         if ( this.clientAction != null ) {
            this.anchorTag.onclick = this.clientAction;
            
         }
      }
      else if ( this.submenu != null ) {
         this.anchorTag.href = "javascript:void(0);";
         this.anchorTag.onclick = menuItemShowSubmenu;
      }
      else if ( this.clientAction != null ) {
         this.anchorTag.onclick = this.clientAction;
      }
      this.anchorTag.onfocus = menuItemFocus;
      this.anchorTag.onblur = menuItemBlur;
      this.anchorTag.onkeydown = menuItemKeyDown;
      this.anchorTag.innerHTML = this.text;
      //this.anchorTag.className = "pop5";   //@05C1
      this.anchorTag.className = this.menuStyle;   //@05C1
      //this.anchorTag.hideFocus = true;
      if ( this.isDefault ) {
         this.anchorTag.style.fontWeight = "bold";
      }

      var td = document.createElement( "TD" );
      td.noWrap = true;
      td.appendChild( this.anchorTag );

      // left padding or icon
      var leftPad = document.createElement( "TD" );
      leftPad.noWrap = true;
      if ( this.icon != null ) {
         var imgTag = document.createElement( "IMG" );
         imgTag.src = this.icon;
         if ( this.text != null ) {
            imgTag.alt = this.text;
            imgTag.title = this.text;
         }
         leftPad.appendChild( imgTag );
      }
      else {
         leftPad.width = padding_;
      }

      // right padding
      var rightPad = document.createElement( "TD" );
      rightPad.noWrap = true;
      rightPad.width = padding_;

      this.itemTag = document.createElement( "TR" );
      this.itemTag.onmousemove = menuItemMouseMove;
      this.itemTag.onmousedown = menuItemMouseDown;
      //this.itemTag.className = "pop5";  //@05C1
      this.itemTag.className = this.menuStyle;  //@05C1   

      // put together the table row
      this.itemTag.appendChild( leftPad );
      this.itemTag.appendChild( td );
      this.itemTag.appendChild( rightPad );
      if ( menuHasSubmenu ) {
         // submenu arrow
         var submenuArrow = document.createElement( "TD" );
         submenuArrow.noWrap = true;
         if ( this.submenu != null ) {
            var submenuImg = document.createElement( "IMG" );
            submenuImg.alt = submenuAltText_;
            submenuImg.title = submenuAltText_;
            submenuImg.width = arrowWidth_;
            submenuImg.height = arrowHeight_;
            if (this.parentMenu.isLTR) submenuImg.src = arrowNorm_;
            else submenuImg.src = arrowNormRTL_;
            submenuArrow.appendChild( submenuImg );
            this.arrowTag = submenuImg;
         }
         this.itemTag.appendChild( submenuArrow );
      }

      // update the style of the menu item
      this.updateStyle( this.itemTag );
   }
}

// create the context menu separator html elements
function UilMenuItemCreateSeparator( tableBody, menuHasSubmenu ) {
   // create the context menu separator
   var numCols = ( menuHasSubmenu ) ? 4 : 3;

   for ( var i=0; i<4; i++ ) {
      var tr = document.createElement( "TR" );
      if ( i == 1 ) {
         //tr.className = "pop3";    //@05C1
         tr.className = "portlet-separator";    //@05C1 
      }
      else if ( i == 2 ) {
         //tr.className = "pop4";  //@05C1
         tr.className = "lwpMenuBackground";    //@05C1
      }
      else {
         //tr.className = "pop5";  //@05C1
         tr.className = "lwpMenuItem";  //@05C1
      }

      var td = document.createElement( "TD" );
      td.noWrap = true;
      td.width = "100%";
      td.colSpan = numCols;

      tr.appendChild( td );
      tableBody.appendChild( tr );
   }
}

// changes the selected state for menu item
function UilMenuItemSetSelected( isSelected ) {

   if ( isSelected && !this.isSelected ) {
      debug( 'selected: ' + this.text );
      // handle the previous selection first
      if ( this.parentMenu != null &&
           this.parentMenu.isVisible &&
           this.parentMenu.selectedItem != null &&
           this.parentMenu.selectedItem != this ) {
         // hide previous selection's submenu
         if ( this.parentMenu.selectedItem.submenu != null ) {
            this.parentMenu.selectedItem.submenu.hide();
         }
         // unselect previous selection from parent menu
         this.parentMenu.selectedItem.setSelected( false );
      }

      // select this menu item
      this.isSelected = true;
      if ( this.parentMenu != null && this.parentMenu.isVisible ) {
         this.parentMenu.selectedItem = this;
      }

      // update the styles
      this.updateStyle( this.itemTag );
   }
   else if ( !isSelected && this.isSelected ) {
      // menu item cannot be unselected if its submenu is visible
      if ( this.submenu == null || ( this.submenu != null && !this.submenu.isVisible ) ) {
         // unselect this menu item
         this.isSelected = false;
         if ( this.parentmenu != null ) {
            this.parentmenu.selectedItem = null;
         }

         // update the styles
         this.updateStyle( this.itemTag );
      }
   }
}

// recursively set the style of the menu item html element
function UilMenuItemUpdateStyle( tag, styleID ) {
   if ( tag != null ) {
      if ( styleID == null ) {
         //styleID = "pop5";   //@05C1
         styleID = this.menuStyle;
         if ( !this.isEnabled ) {
            //styleID = "pop7";    //@05C1
            styleID = this.menuStyle;
         }
         else if ( this.isSelected ) {
            //styleID = "pop6";  //@05C1
            styleID = this.selectedMenuStyle;
            
         }
         if ( this.arrowTag != null ) {
            if ( this.isEnabled && this.isSelected ) {
               if (this.parentMenu.isLTR) this.arrowTag.src = arrowSel_;
               else this.arrowTag.src = arrowSelRTL_;
            }
            else if ( !this.isEnabled ) {
               if (this.parentMenu.isLTR) this.arrowTag.src = arrowDis_;
               else this.arrowTag.src = arrowDisRTL_;
            }
            else {
               if (this.parentMenu.isLTR) this.arrowTag.src = arrowNorm_;
               else this.arrowTag.src = arrowNormRTL_;
            }
         }
      }
      tag.className = styleID;
      if ( tag.children != null ) {
         for ( var i=0; i<tag.children.length; i++ ) {
            this.updateStyle( tag.children[i], styleID );
         }
      }
   }
}

// returns the next enabled, non-separator menu item after the given item
function UilMenuItemGetNextItem() {
   var menu = this.parentMenu;
   if ( menu != null ) {
      for ( var i=0; i<menu.items.length; i++ ) {
         if ( menu.items[i] == this ) {
            for ( var j=i+1; j<menu.items.length; j++ ) {
               if ( !menu.items[j].isSeparator && menu.items[j].isEnabled ) {
                  return menu.items[j];
               }
            }
            // no next item
            return null;
         }
      }
   }
   return null;
}

// returns the previous enabled, non-separator menu item before the given item
function UilMenuItemGetPrevItem() {
   var menu = this.parentMenu;
   if ( menu != null ) {
      for ( var i=menu.items.length-1; i>=0; i-- ) {
         if ( menu.items[i] == this ) {
            for ( var j=i-1; j>=0; j-- ) {
               if ( !menu.items[j].isSeparator && menu.items[j].isEnabled ) {
                  return menu.items[j];
               }
            }
            // no previous item
            return null;
         }
      }
   }
   return null;
}

// launches the action for a menu item
// method called by an event handler (href for anchor tag)
function menuItemLaunchAction() {
   if ( visibleMenu_ != null ) {
      //var evt = window.event;
      //var item = visibleMenu_.getMenuItem( evt.srcElement );
      var item = visibleMenu_.getSelectedItem();
      if ( item != null && item.isEnabled ) {
         hideCurrentContextMenu( true );
         if ( item.clientAction != null ) {
            eval( item.clientAction );
         }
         if ( item.action != null ) {
            if ( item.action.indexOf( "javascript:" ) == 0 ) {
               eval( item.action );
            }
            else {
               //window.location.href = item.action; //@03D
            }
         }
      }
   }
}

// shows the submenu for a menu item
// method called by an event handler (onclick for anchor tag)
function menuItemShowSubmenu() {
   if ( visibleMenu_ != null ) {
      var evt = window.event;
      var item = visibleMenu_.getMenuItem( evt.srcElement );
      if ( item != null && item.isEnabled ) {
         var menu = item.submenu;
         if ( menu != null ) {
            menu.show( item.anchorTag, item );
         }
      }
   }
}

// focus handler for menu item
// method called by an event handler (onfocus for anchor tag)
function menuItemFocus() {
   if ( visibleMenu_ != null ) {
      var evt = window.event;
      var item = visibleMenu_.getMenuItem( evt.srcElement );
      if ( item != null ) {
         // select the focused menu item
         //item.anchorTag.hideFocus = item.isEnabled;
         item.setSelected( true );
      }
   }
}

// blur handler for menu item
// method called by an event handler (onblur for anchor tag)
function menuItemBlur() {
   if ( visibleMenu_ != null ) {
      var evt = window.event;
      var item = visibleMenu_.getMenuItem( evt.srcElement );
      if ( item != null ) {
         if ( item.isFirst && evt.shiftKey ) {
            // user is shift tabbing off the beginning of the menu
            // set focus on the launcher
            item.parentMenu.launcher.focus();
            // hide the menu
            item.parentMenu.hide();
         }
      }
   }
}

// key press handler for menu item
// method called by an event handler (onkeydown for anchor tag)
function menuItemKeyDown() {
   debug( 'menuitem keydown' );
   var item = null;
   if ( visibleMenu_ != null ) {
      var evt = window.event;
      item = visibleMenu_.getMenuItem( evt.srcElement );
   }
   if ( item != null ) {
      var next = null;
      switch ( evt.keyCode ) {
      case 38: // up key
         next = item.getPrevItem();
         if ( next != null ) {
            next.anchorTag.focus();
         }
         else if ( item.parentMenu != visibleMenu_ ) {
            item.parentMenu.launcher.focus();
            item.parentMenu.hide();
         }
         else {
            visibleMenu_.launcher.focus();
            hideCurrentContextMenu( true );
         }
         break;
      case 40: // down key
         next = item.getNextItem();
         if ( next != null ) {
            next.anchorTag.focus();
         }
         else if ( item.parentMenu != visibleMenu_ ) {
            item.parentMenu.launcher.focus();
            item.parentMenu.hide();
         }
         else {
            visibleMenu_.launcher.focus();
            hideCurrentContextMenu( true );
         }
         break;
      case 39: // right key
         if ( visibleMenu_.isLTR ) {
            if ( item.submenu != null ) {
               menuItemShowSubmenu(evt);
               item.submenu.items[0].anchorTag.focus();
            }
         }
         else {
            if ( item.parentMenu != visibleMenu_ ) {
               item.parentMenu.launcher.focus();
               item.parentMenu.hide();
            }
         }
         break;
      case 37: // left key
         if ( visibleMenu_.isLTR ) {
            if ( item.parentMenu != visibleMenu_ ) {
               item.parentMenu.launcher.focus();
               item.parentMenu.hide();
            }
         }
         else {
            if ( item.submenu != null ) {
               menuItemShowSubmenu(evt);
               item.submenu.items[0].anchorTag.focus();
            }
         }
         break;
      case 9: // tab key
         visibleMenu_.launcher.focus();
         hideCurrentContextMenu( true );
         break;
      case 27: // escape key
         visibleMenu_.launcher.focus();
         hideCurrentContextMenu( true );
         break;
      case 13: // enter key
         break;
      default:
         break;
      }
   }
}

// handle mouse move for menu item
// method called by an event handler (onmousemove for item tag)
function menuItemMouseMove() {
   if ( visibleMenu_ != null ) {
      var evt = window.event;
      var item = visibleMenu_.getMenuItem( evt.srcElement );
      if ( item != null ) {
         if ( !item.isSelected ) {
            // set focus on the anchor and select the menu item
            item.anchorTag.focus();
         }
         if ( item.submenu != null && !item.submenu.isVisible && item.isEnabled ) {
            // show the submenu
            item.submenu.show( item.anchorTag, item );
         }
      }
   }
}

// handle mouse down event for menu item
// method called by an event handler (onmousedown for item tag)
function menuItemMouseDown() {
   if ( visibleMenu_ != null ) {
      var evt = window.event;
      var item = visibleMenu_.getMenuItem( evt.srcElement );
      if ( item != null ) {
         item.setSelected( true );
         if ( item.anchorTag != evt.srcElement ) {
            item.anchorTag.click();
         }
      }
   }
}

var allMenus_ = new Array();

function createContextMenu( name, isLTR, width ) {
   var menu = new UilContextMenu( name, isLTR, width );
   allMenus_[ allMenus_.length ] = menu;
   return menu;
}

function getContextMenu( name ) {
   for ( var i=0; i<allMenus_.length; i++ ) {
      if ( allMenus_[i].name == name ) {
         return allMenus_[i];
      }
   }
   return null;
}

function showContextMenu( name, isDynamic, isCacheable ) {
   contextMenuShow( name, isDynamic, isCacheable, window.event.srcElement, true );
   clearMenuTimer( );
}

function contextMenuShow( name, isDynamic, isCacheable, launcher, doLoad ) {
   debug( "***** showContextMenu: " + name )

   if ( eval( isDynamic ) ) {
      debug( 'showContextMenu: dynamic=true, load=' + eval( doLoad ) + ', cache=' + eval( isCacheable ) );
      // dynamically loaded menu
      if ( eval( doLoad ) ) {
         // load the url into hidden frame
         loadDynamicMenu( name );
      }

      // clone the dynamic menu from hidden frame
      menu = getDynamicMenu( name, eval( isCacheable ) );

      if ( menu == null && top.isContextMenuManager_ != null ) {
         // menu not done loading yet
         debug( 'showContextMenu: ' + name + ' added to queue' );
         top.contextMenuManagerRequest( name, window, launcher, isCacheable );
      }
   }
   else {
      debug( 'showContextMenu: static context menu' );
      // statically defined menu
      menu = getContextMenu( name );
      if ( menu == null ) {
         menu = createContextMenu( name, 150 );
      }
   }

   if ( menu != null ) {
      hideCurrentContextMenu( true );
      menu.show( launcher );
      visibleMenu_ = menu;
   }
   else {
      debug( 'showContextMenu: ' + name + ' unavailable' );
   }
}

// method called by an event handler (onmousedown for document)
function hideCurrentContextMenu( forceHide ) {
   if ( visibleMenu_ != null && ( forceHide || visibleMenu_.isDismissable ) ) {
      contextMenuDismissEnable();
      if ( visibleMenu_.isVisible ) {
         visibleMenu_.hide();
      }
      if ( visibleMenu_.isDynamic && !visibleMenu_.isCacheable ) {
         uncacheContextMenu( visibleMenu_ );
      }
      visibleMenu_ = null;
   }
}

function uncacheContextMenu( menu ) {
   debug( 'uncache menu: ' + menu.name );
   // recurse
   for ( var i=0; i<menu.items.length; i++ ) {
      if ( menu.items[i].submenu != null ) {
         uncacheContextMenu( menu.items[i].submenu );
      }
   }

   // remove from all menus array
   for ( var i=0; i<allMenus_.length; i++ ) {
      if ( allMenus_[i] == menu ) {
         var temp = new Array();
         var index = 0;
         for ( var j=0; j<allMenus_.length; j++ ) {
            if ( j != i ) {
               temp[ index ] = allMenus_[ j ];
               index++;
            }
         }
         allMenus_ = temp;
         break;
      }
   }
}

function contextMenuSetIcons( transparentImage,
                              arrowDefault, arrowSelected, arrowDisabled,
                              launcherDefault, launcherSelected,
                              arrowDefaultRTL, arrowSelectedRTL, arrowDisabledRTL,
                              launcherDefaultRTL, launcherSelectedRTL ) {
   transImg_ = transparentImage;

   arrowNorm_ = arrowDefault;
   arrowSel_ = arrowSelected;
   arrowDis_ = arrowDisabled;
   launchNorm_ = launcherDefault;
   launchSel_ = launcherSelected;

   arrowNormRTL_ = arrowDefaultRTL;
   arrowSelRTL_ = arrowSelectedRTL;
   arrowDisRTL_ = arrowDisabledRTL;
   launchNormRTL_ = launcherDefaultRTL;
   launchSelRTL_ = launcherSelectedRTL;

   contextMenuPreloadImage( transImg_ );

   contextMenuPreloadImage( arrowNorm_ );
   contextMenuPreloadImage( arrowSel_ );
   contextMenuPreloadImage( arrowDis_ );
   contextMenuPreloadImage( launchNorm_ );
   contextMenuPreloadImage( launchSel_ );

   contextMenuPreloadImage( arrowNormRTL_ );
   contextMenuPreloadImage( arrowSelRTL_ );
   contextMenuPreloadImage( arrowDisRTL_ );
   contextMenuPreloadImage( launchNormRTL_ );
   contextMenuPreloadImage( launchSelRTL_ );
}

function contextMenuSetArrowIconDimensions( width, height ) {
   arrowWidth_ = width;
   arrowHeight_ = height;
}

function contextMenuPreloadImage( imgsrc ) {
   var preload = new Image();
   preload.src = imgsrc;
}

function toggleLauncherIcon( popupID, selected, isLTR ) {
   if ( selected ) {
      if ( isLTR ) {
         document.images[ popupID ].src = launchSel_;
      }
      else {
         document.images[ popupID ].src = launchSelRTL_;
      }
   }
   else {
      if ( isLTR ) {
         document.images[ popupID ].src = launchNorm_;
      }
      else {
         document.images[ popupID ].src = launchNormRTL_;
      }
   }
   return true;
}

function contextMenuSetNoActionsText( noActionsText, submenuAltText ) {
   noActionsText_ = noActionsText;
   submenuAltText_ = submenuAltText;
}

function contextMenuGetNoActionsText() {
   return noActionsText_;
}

function getWidth( tag ) {
   return tag.offsetWidth;
}

function getHeight( tag ) {
   return tag.offsetHeight;
}

function getLeft( tag, recurse ) {
   var size = 0;
   if ( recurse && tag.offsetParent != null ) {
      size += getLeft( tag.offsetParent, recurse );
   }
   if ( tag != null ) {
      size += tag.offsetLeft;
   }
   return size;
}

function getTop( tag, recurse ) {
   var size = 0;
   if ( recurse && tag.offsetParent != null ) {
      size += getTop( tag.offsetParent, recurse );
   }
   if ( tag != null ) {
      size += tag.offsetTop;
   }
   return size;
}

/*****************************************************
* code for dynamically loaded menus
*****************************************************/
function loadDynamicMenu( menuURL ) {
   debug( '* loadDynamicMenu: ' + menuURL );
   var menu = getContextMenu( menuURL );
   if ( menu != null ) {
      if ( menu.isVisible ) {
         // dynamic menu requested, but it's currently showing
         menu.hide();
      }
      if ( !menu.isCacheable ) {
         // make sure it's not in the cache
         uncacheContextMenu( menu );
      }
   }
   if ( getContextMenu( menuURL ) == null ) {
      if ( top.isContextMenuManager_ != null ) {
         debug( 'loadDynamicMenu: loading' );
         top.contextMenuManagerLoadDynamicMenu( menuURL );
      }
   }
}

function getDynamicMenu( menuURL, cache ) {
   debug( '* getDynamicMenu: ' + menuURL );
   var clone = getContextMenu( menuURL );
   if ( clone == null ) {
      if ( top.isContextMenuManager_ != null ) {
         if ( top.contextMenuManagerIsDynamicMenuLoaded() ) {
            var menu = top.contextMenuManagerGetDynamicMenu();
            debug( 'getDynamicMenu: fetched menu from other frame' );
            clone = cloneMenu( menu, menuURL, cache );
            if ( clone.items.length == 0 ) {
               contextMenuSetNoActionsText( top.contextMenuManagerGetNoActionsText() );
            }
         }
         else {
            debug( 'getDynamicMenu: menu not loaded' );
         }
      }
      else {
         debug( 'getDynamicMenu: menu manager not present' );
      }
   }
   else {
      debug( 'getDynamicMenu: menu previously loaded' );
   }
   return clone;
}

function cloneMenu( menu, name, cache ) {
   var clone = getContextMenu( name );
   if ( clone == null ) {
      if ( menu != null ) {
         clone = createContextMenu( name, menu.width );
      }
      else {
         clone = createContextMenu( name, 150 );
      }
      clone.isDynamic = true;
      clone.isCacheable = cache;
      if ( menu != null ) {
         for ( var i=0; i<menu.items.length; i++ ) {
            clone.add( cloneMenuItem( menu.items[i], name + "_sub" + i, cache ) );
         }
      }
   }
   return clone;
}

function cloneMenuItem( item, submenuName, cache ) {
   var submenu = null;
   if ( item.submenu != null ) {
      submenu = cloneMenu( item.submenu, submenuName, cache );
   }
   var clone = new UilMenuItem( item.text, item.isEnabled, item.action, item.clientAction, submenu, item.icon, null, null, null );
   clone.isEnabled = item.isEnabled;
   clone.isSelected = item.isSelected;
   clone.isSeparator = item.isSeparator;
   return clone;
}

