<%@ page import="com.jondejong.mongo.demo.Family" %>



<div class="fieldcontain ${hasErrors(bean: familyInstance, field: 'familyName', 'error')} ">
	<label for="familyName">
		<g:message code="family.familyName.label" default="Family Name" />
		
	</label>
	<g:textField name="familyName" value="${familyInstance?.familyName}" />

</div>

<div class="fieldcontain ${hasErrors(bean: familyInstance, field: 'address.line1', 'error')} ">
    <label for="familyName">
        <g:message code="family.address.line1.label" default="Address Line 1" />

    </label>
    <g:textField name="address.line1" value="${familyInstance?.address?.line1}" />

</div>
<div class="fieldcontain ${hasErrors(bean: familyInstance, field: 'address.city', 'error')} ">
    <label for="familyName">
        <g:message code="family.address.city.label" default="City" />

    </label>
    <g:textField name="address.city" value="${familyInstance?.address?.city}" />

</div>

<div class="fieldcontain ${hasErrors(bean: familyInstance, field: 'address.state', 'error')} ">
    <label for="familyName">
        <g:message code="family.address.state.label" default="State" />

    </label>
    <g:textField name="address.state" value="${familyInstance?.address?.state}" />

</div>
<div class="fieldcontain ${hasErrors(bean: familyInstance, field: 'address.postalCode', 'error')} ">
    <label for="familyName">
        <g:message code="family.address.postalCode.label" default="Postal Code" />

    </label>
    <g:textField name="address.postalCode" value="${familyInstance?.address?.postalCode}" />

</div>

<div class="fieldcontain ${hasErrors(bean: familyInstance, field: 'people', 'error')} ">
	<label for="people">
		<g:message code="family.people.label" default="People" />
		
	</label>
	<g:select name="people" from="${com.jondejong.mongo.demo.Person.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${familyInstance?.people*.id}" class="many-to-many"/>

</div>

