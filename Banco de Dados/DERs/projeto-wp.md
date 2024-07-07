# Project - WP

- Transfer the following verbal description to an Entity-Relationship Model (MER):

1. A project (Proj-#) consists of several work packages (WP-#).
2. Work packages are structured in a hierarchical work decomposition structure, where a work package can be subdivided into several other work packages, while each work package is subordinate to exactly one work package.
3. One or more employees (SI-ID) are assigned to each work package, where employees can also be employed in multiple work packages. Employees have certain qualifications (Qual-ID) and are uniquely assigned to departments (Dept-ID).
4. A work package may require one or more qualifications.
5. Each project has an employee as the responsible person; however, an employee may also have responsibility for multiple projects.

---

![WP Project - DER](https://i.imgur.com/kUtpblQ.png)