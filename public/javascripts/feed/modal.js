/************************************************************************************
 * Modal creator
 ************************************************************************************/
var Modal = (function() {
    // This reload, will ensure that the JQuery search are made when Angular load all HTML
    var reload = function(){
        trigger = $('.modal__trigger'); // Button to activate the Modal
        modals = $('.modal');           // Modal (takes up entire window)
        modalsbg = $('.modal__bg');     // Bigger Modal (takes up entire window)
        content = $('.modal__content'); // Content of the Modal
        closers = $('.modal__close');   // Button to close the Modal
        w = window;
        isOpen = false;
        contentDelay = 400;             // Duration for the content to show after open Modal
        len = trigger.length;
    };

    var trigger, modals, modalsbg, content, closers, w, isOpen, contentDelay, len;
    reload();

    // Parse IDs on elements
    var getId = function(event) {
        event.preventDefault();
        var self = this;
        // Get the data-modal attribute from the button
        var modalId = self.dataset.modal;
        var len = modalId.length;
        // Remove the '#' from the string
        var modalIdTrimmed = modalId.substring(1, len);
        // Select the modal we want to activate
        var modal = document.getElementById(modalIdTrimmed);
        // Creates the temporary expanding div
        makeDiv(self, modal);
    };

    var makeDiv = function(self, modal) {

        var fakediv = document.getElementById('modal__temp');

        /**
         * If there isn't a 'fakediv', create one and append it to the button that was
         * clicked. after that execute the function 'moveTrig' which handles the animations.
         */

        if (fakediv === null) {
            var div = document.createElement('div');
            div.id = 'modal__temp';
            self.appendChild(div);
            moveTrig(self, modal, div);
        }
    };

    var moveTrig = function(trig, modal, div) {
        var trigProps = trig.getBoundingClientRect();
        var m = modal;
        var mProps = m.querySelector('.modal__content').getBoundingClientRect();
        var transX, transY, scaleX, scaleY;
        var xc = w.innerWidth / 2;
        var yc = w.innerHeight / 2;

        // This class increases z-index value so the button goes overtop the other buttons
        trig.classList.add('modal__trigger--active');

        // These values are used for scale the temporary div to the same size as the modal
        scaleX = mProps.width / trigProps.width;
        scaleY = mProps.height / trigProps.height;

        scaleX = scaleX.toFixed(3); // round to 3 decimal places
        scaleY = scaleY.toFixed(3);


        // These values are used to move the button to the center of the window
        transX = Math.round(xc - trigProps.left - trigProps.width / 2);
        transY = Math.round(yc - trigProps.top - trigProps.height / 2);

        // If the modal is aligned to the top then move the button to the center-y of the modal instead of the window
        if (m.classList.contains('modal--align-top')) {
            transY = Math.round(mProps.height / 2 + mProps.top - trigProps.top - trigProps.height / 2);
        }


        // Translate button to center of screen
        trig.style.transform = 'translate(' + transX + 'px, ' + transY + 'px)';
        trig.style.webkitTransform = 'translate(' + transX + 'px, ' + transY + 'px)';
        // Expand temporary div to the same size as the modal
        div.style.transform = 'scale(' + scaleX + ',' + scaleY + ')';
        div.style.webkitTransform = 'scale(' + scaleX + ',' + scaleY + ')';


        window.setTimeout(function() {
            window.requestAnimationFrame(function() {
                open(m, div);
            });
        }, contentDelay);

    };

    var open = function(m, div) {

        if (!isOpen) {
            // Select the content inside the modal
            var content = m.querySelector('.modal__content');
            // Reveal the modal
            m.classList.add('modal--active');
            // Reveal the modal content
            content.classList.add('modal__content--active');

            /**
             * When the modal content is finished transitioning, fadeout the temporary
             * expanding div so when the window resizes it isn't visible ( it doesn't
             * move with the window).
             */

            content.addEventListener('transitionend', hideDiv, false);

            isOpen = true;

            $('<div class="modal-backdrop"></div>').appendTo(document.body);
        }

        function hideDiv() {
            // Fadeout div so that it can't be seen when the window is resized
            div.style.opacity = '0';
            content.removeEventListener('transitionend', hideDiv, false);
        }
    };

    var close = function(event) {

        event.preventDefault();
        event.stopImmediatePropagation();

        var target = event.target;
        var div = document.getElementById('modal__temp');

        /**
         * Make sure the modal__bg or modal__close was clicked, we don't want to be able to click
         * inside the modal and have it close.
         */

        if (isOpen && target.classList.contains('modal__bg') || target.classList.contains('modal__close')) {

            // Make the hidden div visible again and remove the transforms so it scales back to its original size
            div.style.opacity = '1';
            div.removeAttribute('style');

            /**
             * Iterate through the modals and modal contents and triggers to remove their active classes.
             * remove the inline css from the trigger to move it back into its original position.
             */

            reload();

            if(len > 1){
                for (var i = 0; i < len; i++) {
                    if(modals[i])
                        modals[i].classList.remove('modal--active');
                    if(content[i])
                        content[i].classList.remove('modal__content--active');
                    if(trigger[i]){
                        trigger[i].style.transform = "";
                        trigger[i].style.webkitTransform = "";
                        trigger[i].classList.remove('modal__trigger--active');
                    }
                }
            } else {
                modals.classList.remove('modal--active');
                content.classList.remove('modal__content--active');
                trigger.style.transform = "";
                trigger.style.webkitTransform = "";
                trigger.classList.remove('modal__trigger--active');
            }
            // When the temporary div is opacity:1 again, we want to remove it from the dom
            div.addEventListener('transitionend', removeDiv, false);

            isOpen = false;

        }

        function removeDiv() {
            setTimeout(function() {
                window.requestAnimationFrame(function() {
                    // Remove the temp div from the dom with a slight delay so the animation looks good
                    div.remove();
                });
            }, contentDelay - 50);
        }

        $('.modal-backdrop').remove();
    };

    var bindActions = function() {
        for (var i = 0; i < len; i++) {
            if(trigger[i])
                trigger[i].addEventListener('click', getId, false);
            if(closers[i])
                closers[i].addEventListener('click', close, false);
            if(modalsbg[i])
                modalsbg[i].addEventListener('click', close, false);
        }
    };

    var init = function() {
        reload();
        bindActions();
    };

    return {
        init: init
    };

}());
