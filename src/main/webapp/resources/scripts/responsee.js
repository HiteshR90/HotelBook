//Responsee eside nav
jQuery(document).ready(function(){
$('.aside-nav > ul > li ul').each(function(index, element){
  var count = $(element).find('li').length;
  var content = '<span class="count-number"> ' + count + '</span>';
  $(element).closest('li').children('a').append(content);
});

$('.aside-nav > ul > li:has(ul)').addClass('aside-submenu');  
$('.aside-nav > ul ul > li:has(ul)').addClass('aside-sub-submenu');  

$('.aside-nav > ul > li.aside-submenu > a').click(function() {
$('.aside-nav ul li.aside-submenu:hover > ul' ).toggleClass( 'show-aside-ul', 500, 'linear' );
});

$('.aside-nav > ul ul > li.aside-sub-submenu > a').click(function() {
$('.aside-nav ul ul li:hover > ul').toggleClass( 'show-aside-ul', 500, 'linear' );
});

//Responsee nav   
$('.top-nav > ul > li ul').each(function(index, element){
  var count = $(element).find('li').length;
  var content = '<span class="count-number"> ' + count + '</span>';
  $(element).closest('li').children('a').append(content);
});

$('.top-nav > ul > li:has(ul)').addClass('submenu');  
$('.top-nav > ul ul > li:has(ul)').addClass('sub-submenu'); 

$('.top-nav > ul > li.submenu > a').click(function() {
$('.top-nav ul li.submenu:hover > ul').toggleClass( 'show-ul', 500, 'linear' );
});

$('.top-nav > ul ul > li.sub-submenu > a').click(function() {
$('.top-nav ul ul li:hover > ul').toggleClass( 'show-ul', 500, 'linear' );
});

$('.nav-text').toggle(function() { 
$('.top-nav > ul').addClass('show-menu');
}, function() { 
$('.top-nav > ul').removeClass('show-menu');
});
});

//Custom forms
(function($) {
	$.fn.customform = function(settings) {
		settings = jQuery.extend({
			Version: 		'1.0'
		}, settings);		
		var jQueryMatchedObj = this;
		Run(this, jQueryMatchedObj);
		function Run(objForm, jQueryMatchedObj) {
			for ( var idx = 0; idx < jQueryMatchedObj.length; idx++ ) {
				var form = $(jQueryMatchedObj[idx]);
				if(!$(form).attr('id')) { $(form).attr('id', 'custom-' + idx); }
				$(form).setTabIndexes();
				$(form).CustomCheckBoxes();
				$(form).CustomRadioButtons();
				$(form).CustomInputBoxes();
				$(form).CustomSelectBoxes();
				$(form).CustomSelectBoxesM();
				$(form).CustomTextAreas();
				$(form).CustomButtons();
				$(form).CustomFiles();
			}
		}
		return;
	};
	$.fn.setTabIndexes = function() {
		$(this).find('select, input:not(:hidden), textarea, button').each(function(i, ctrl) {
			$(ctrl).attr('tabindex', (i + 1));
		});
	}
	$.fn.CustomCheckBoxes = function() {
		var objForm = this;
		$(objForm).find('input[type="checkbox"]').each(function(i, ctrl) {
			$(ctrl).hide();	        
			var id = 'customcheckbox-' + $(objForm).attr('id') + '-' + (i + 1); 
			if($(ctrl).attr('id')) { id = 'customcheckbox-' + $(ctrl).attr('id'); }     
	        $(ctrl).after('<b class="customcheckbox' + ($(ctrl).attr('checked') ? ' checked' : '') + '" id="' + id + '">&nbsp;</b>');
	        var nCtrl = $('#' + id);
			if($(ctrl).attr('disabled')) { 
				$(nCtrl).addClass('chbx-disabled');
				return;
			}
			$(ctrl).bind('click', function () {
	        	if(!$(ctrl).attr('checked')) { $(nCtrl).removeClass('checked'); } else { $(nCtrl).addClass('checked'); }
	        });
	        $(nCtrl).bind('click', function () {
				if($(ctrl).attr('checked')) {
					$(ctrl).attr('checked', false);
					$(nCtrl).removeClass('checked');
				} else {
					$(ctrl).attr('checked', true);
					$(nCtrl).addClass('checked');
				}
	        });        
	        $('label[for="' + $(ctrl).attr('id') + '"]').bind('click', function () {
				if($(ctrl).attr('checked')) {
					$(ctrl).attr('checked', false);
					$(nCtrl).removeClass('checked');
				} else {
					$(ctrl).attr('checked', true);
					$(nCtrl).addClass('checked');
				}
	        });
	    });
	}
	$.fn.CustomRadioButtons = function() {
		var objForm = this;
		$(objForm).find('input[type="radio"]').each(function(i, ctrl) {
			$(ctrl).hide();
			var id = 'customradio-' + $(objForm).attr('id') + '-' + (i + 1);		
			if($(ctrl).attr('id')) { id = 'customradio-' + $(ctrl).attr('id'); }
	        $(ctrl).after('<a rel="' + $(ctrl).attr('name') + '" class="customradio' + ($(ctrl).attr('checked') ? ' checked' : '') + '" id="' + id + '">&nbsp;</a>');	        
			var nCtrl = $('#' + id); 			
			if($(ctrl).attr('disabled')) { 
				$(nCtrl).addClass('rbtn-disabled');
				return;
			}			
			$(ctrl).bind('click', function () {
				$('.customradio[rel="' + $(ctrl).attr('name') + '"]').removeClass('checked');
				$(nCtrl).addClass('checked');
	        });
	        $(nCtrl).bind('click', function () {
				$('.customradio[rel="' + $(nCtrl).attr('rel') + '"]').removeClass('checked');
				$('input[name="' + $(nCtrl).attr('rel') + '"]').attr('checked', false);				
				$(this).addClass('checked');
				$(ctrl).attr('checked', true);			
				return false;
	        });
	    });			
	}
	$.fn.CustomInputBoxes = function() {
		var objForm = this;		
		$(objForm).find('input[type="text"],input[type="password"]').each(function (i, ctrl) {
	    	var id = 'custominput-' + $(objForm).attr('id') + '-' + (i + 1);
	    	if($(ctrl).attr('id')) { 
	    		id = 'custominput-' + $(ctrl).attr('id'); 
	    	}
	    	$(ctrl).wrap('<span class="custominputut" id="' + id + '"><span></span></span>');
	    	var nCtrl = $('#' + id);
	    	if($(ctrl).attr('disabled')) { $(nCtrl).addClass('disabled'); }
	    	$(nCtrl).swapStyles($(ctrl));
	    	$(ctrl).bind('focus', function () { 
	    		if($(ctrl).val() == $(ctrl).attr('title')) { $(ctrl).val(''); } 
	    		$(nCtrl).CtrlInFocus();
	    	});
			$(ctrl).bind('blur', function () { 
				if($(ctrl).val() == '') { $(ctrl).val($(ctrl).attr('title')); }
				$(nCtrl).CtrlOutFocus();
			});
	    });		
	}
	$.fn.CustomSelectBoxes = function() {
		var objForm = this;
		var cite = 'strong span cite';		
	    $(objForm).find('select').each(function (i, ctrl) {			
			if($(ctrl).attr('multiple')) { return; }			
			var width = '100%';
	    	var id = 'select-' + $(objForm).attr('id') + '-' + (i + 1);
	    	if($(ctrl).attr('id')) { 
	    		id = $(ctrl).attr('id');
	    		$(ctrl).attr('id', '');
	    	}
	    	$(ctrl).width('100%');
	    	var selectedTxt = ($(ctrl).find('option:selected').text() != '' ? $(ctrl).find('option:selected').text() : '&nbsp;');
	    	$(ctrl).before('<div class="customselect" tabindex="' + $(ctrl).attr('tabindex') + '" id="' + id + '"><strong><span><cite>' + selectedTxt + '</cite></span></strong><ul></ul></div>');
	    	$(ctrl).attr('tabindex', '');
	    	$(ctrl).hide();
	    	var nCtrl = $('#' + id);
	    	$(nCtrl).swapStyles($(ctrl));
	    	$(nCtrl).bind('click', function () { $(this).find('ul').show(); });
	    	$(nCtrl).bind('mouseleave', function () { $(this).find('ul').hide(); });
	    	$(nCtrl).bind('focus', function () { 
	    		$('body').focus();
	    		$('.customselect').css('z-index', '100');
	    		$(nCtrl).css('z-index', '1500'); 
	    		$(this).CtrlInFocus();
	    	});
			$(nCtrl).bind('blur', function () { $(this).CtrlOutFocus(); });
	    	$(ctrl).find('option').each(function (idx, item) {
	    		nCtrl.find('ul').append('<li option="' + $(item).attr('value') + '">' + $(item).text() + '</li>');
	    	});
	    	nCtrl.find('li').each(function(num, elem) {
	    		$(elem).bind('mouseenter', function() { $(this).addClass('active'); });
	    		$(elem).bind('mouseout', function() { $(this).removeClass('active'); });
	    		$(elem).bind('click', function() { 
	    			nCtrl.find(cite).text($(this).text());
	    			$(ctrl).val($(this).attr('option'));
	    			nCtrl.find('ul').fadeOut();
	    		});
	    	});
	    	nCtrl.bind('keydown', function(event) {
	    		var selected = $(ctrl).find('option:selected');
	    		$(nCtrl).find('ul').hide();
	    		if(event.keyCode == 38) {
	    			if(selected.val() != $(ctrl).find('option:first').val()) {
	    				selected.prev().attr('selected', true);
	    				$(nCtrl).find(cite).text(selected.prev().text());
	    			}   
	    			return false;
	    		} 
	    		if(event.keyCode == 40) {
	    			if(selected.val() != $(ctrl).find('option:last').val()) {
	    				selected.next().attr('selected', true);
	    				$(nCtrl).find(cite).text(selected.next().text());
	    			}   
	    			return false;
	    		}	
	    		if(event.keyCode == 33) { 
	    			$(ctrl).find('option:first').attr('selected', true);
					$(nCtrl).find(cite).text($(ctrl).find('option:first').text());
					
	    			return false;
	    		}	
	    		if(event.keyCode == 34) {
	    			$(ctrl).find('option:last').attr('selected', true);
					$(nCtrl).find(cite).text($(ctrl).find('option:last').text());
	
	    			return false;
	    		}	
	    	});
	    	$(ctrl).bind('change', function() {
	    		nCtrl.find(cite).text($(this).find('option:selected').text());	
	    	});
	    });
	}
	$.fn.CustomSelectBoxesM = function() {
		var objForm = this;
		$(objForm).find('select[multiple]').each(function (i, ctrl) {
			var id = 'customselect-' + $(objForm).attr('id') + '-' + (i + 1);
	    	if($(ctrl).attr('id')) { 
	    		id = $(ctrl).attr('id');
	    		$(ctrl).attr('id', '');
	    	}
	    	$(ctrl).wrap('<span class="customselect" id="' + id + '"><span><span><span></span></span></span></span>');
	    	
	    	var nCtrl = $('#' + id);
	    	$(ctrl).bind('focus', function () { $(nCtrl).CtrlInFocus(); });
			$(ctrl).bind('blur', function () { $(nCtrl).CtrlOutFocus(); });
	    });
	}
	$.fn.CustomTextAreas = function() {
		var objForm = this;
		$(objForm).find('textarea').each(function (i, ctrl) {
	    	var id = 'customtextarea-' + $(objForm).attr('id') + '-' + (i + 1);
	    	if($(ctrl).attr('id')) { id = 'customtextarea-' + $(ctrl).attr('id'); }
	    	$(ctrl).wrap('<span class="customtextarea" id="' + id + '"><span><span><span></span></span></span></span>');
	    	var nCtrl = $('#' + id);
	    	if($(ctrl).attr('disabled')) { $(nCtrl).addClass('tx-disabled'); }
	    	
	    	$(ctrl).bind('focus', function () { $(nCtrl).CtrlInFocus(); });
			$(ctrl).bind('blur', function () { $(nCtrl).CtrlOutFocus(); });
	    });
	}
	$.fn.CustomButtons = function() {
		var objForm = this;
		$(objForm).find('button:not(.custombutton)').each(function(i, ctrl) {
			$(ctrl).addClass('custombutton');
			if($(ctrl).attr('type').toLowerCase() == 'submit') { 
				$(ctrl).wrapInner('<strong><span></span></strong>');
			} else { 
				$(ctrl).wrapInner('<span><span></span></span>');
			}
			$(ctrl).bind('focus', function () { $(ctrl).CtrlInFocus(); });
			$(ctrl).bind('blur', function () { $(ctrl).CtrlOutFocus(); });
		});
	}
	$.fn.CustomFiles = function() {
		var objForm = this;
		$(objForm).find('input[type="file"]').each(function (i, ctrl) {
			var width = '100%';
	    	var id = 'customfiles-' + $(objForm).attr('id') + '-' + (i + 1);
	    	if($(ctrl).attr('id')) { 
	    		id = 'customfiles-' + $(ctrl).attr('id'); 
	    	}
	    	$(ctrl).before('<a tabindex="' + $(ctrl).attr('tabindex') + '" class="customfiles" id="' + id + '"><span><cite>Not Selected ...</cite><strong>' + ($(ctrl).attr('title') != '' ? $(ctrl).attr('title') : 'Browse ...') + '</strong></span></a>');
	    	$(ctrl).attr('tabindex', 0);
	   		$(ctrl).addClass('customfileshidden');
	    	var nCtrl = $('#' + id);
	   		$(ctrl).appendTo('#' + id + ' span strong');
	    	if($(ctrl).attr('disabled')) { $(nCtrl).addClass('disabled'); }
	    	$(ctrl).bind('change', function () { $(nCtrl).find('cite').text($(this).attr('value')); });
	    	$(nCtrl).bind('focus', function () { $(nCtrl).CtrlInFocus(); });
			$(nCtrl).bind('blur', function () { $(nCtrl).CtrlOutFocus(); });
	    });
	}
$.fn.swapStyles = function (objSrc) {
		var styles = new Array('margin-left', 'margin-right', 'margin-top', 'margin-bottom');
		$(styles).each(function(idx, property) {
	    	$(this).css(property, $(objSrc).css( property ));
		});
		$(objSrc).addClass('customzero');
	}	
	$.fn.CtrlInFocus = function() { $(this).addClass('customfocus'); }
	$.fn.CtrlOutFocus = function() { $(this).removeClass('customfocus'); }	
})(jQuery); 

$(document).ready(function() {
	$('.customform').customform();
});
//Active item
$(document).ready(function() {
var url = window.location.href;
$('a').filter(function() {
    return this.href == url;
}).parent('li').addClass('active-item');

var url = window.location.href;
$('.aside-nav a').filter(function() {
    return this.href == url;
}).parent('li').parent('ul').addClass('active-aside-item');

var url = window.location.href;
$('.aside-nav a').filter(function() {
    return this.href == url;
}).parent('li').parent('ul').parent('li').parent('ul').addClass('active-aside-item');

var url = window.location.href;
$('.aside-nav a').filter(function() {
    return this.href == url;
}).parent('li').parent('ul').parent('li').parent('ul').parent('li').parent('ul').addClass('active-aside-item');
});