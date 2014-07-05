(function ($) {
    $.fn.message = function(options) {
       this.append($("<div class='alert-message " + options.class + " fade in' data-alert>" +
            "<p> " + options.message + " </p>" +
            "</div>"));

        $(".alert-message").delay(options.delay).fadeOut("slow", function () { $(this).remove(); });
    }
})(jQuery);