### loading testdata into sql tables ?

create a file src/test/resources/data.sql and put your insert statements there.

### how to define sql date columns with "on update current timestamp" ?

Have a "Base" entity class with the following field definition (example for lastchanged):
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date lastchanged;

### What is the way to perform server-side (input) validation ?

### Can I generate all SQL DDL ?

### How to handle security (authentication + authorisation) ?