<%@attribute name="message" required="true"%>
<%@attribute name="delay"%>

<script>
    $("#messagebox").message({message: "${message}", class: "alert alert-danger", delay:${not empty delay ? delay : 1000}});
</script>